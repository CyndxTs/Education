package dev.sportinghub.mobile.repository.posts

import androidx.sqlite.db.SimpleSQLiteQuery
import dev.sportinghub.mobile.client.features.SH_Formatter
import dev.sportinghub.mobile.model.posts.Category
import dev.sportinghub.mobile.model.posts.Post
import dev.sportinghub.mobile.model.posts.PostCategory
import dev.sportinghub.mobile.model.posts.PostType
import dev.sportinghub.mobile.model.posts.PromotionPost
import dev.sportinghub.mobile.model.posts.PublicationPost
import dev.sportinghub.mobile.room.posts.PostCategoryDatabaseDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostCategoryRepository @Inject constructor(private val postCategoryDatabaseDAO: PostCategoryDatabaseDAO) {
    private var conditions = mutableListOf<String>()

    suspend fun insert(postType: PostType,postId: Int,categoryId: Int): Long = postCategoryDatabaseDAO.insert(PostCategory(postType,postId, categoryId))

    suspend fun delete(postType: PostType,postId: Int,categoryId: Int) = postCategoryDatabaseDAO.delete(PostCategory(postType,postId, categoryId))

    fun getPublicationPostsByCategory(model: Category?): Flow<PublicationPost> {
        var sql = "SELECT * FROM PUBLICATION_POST P"
        loadClauseConditions(0)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.JOIN)
        loadClauseConditions(model)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return postCategoryDatabaseDAO.getPublicationPostsByCategory(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    fun getPromotionPostsByCategory(model: Category?): Flow<PromotionPost> {
        var sql = "SELECT * FROM PROMOTION_POST P"
        loadClauseConditions(0)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.JOIN)
        loadClauseConditions(model)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return postCategoryDatabaseDAO.getPromotionPostsByCategory(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    fun getCategoriesByPost(model: Post?): Flow<Category> {
        var sql = "SELECT * FROM CATEGORY C"
        when(model) {
            is PublicationPost -> loadClauseConditions(1)
            else -> loadClauseConditions(2)
        }
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.JOIN)
        loadClauseConditions(model)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return postCategoryDatabaseDAO.getCategoriesByPost(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    private fun loadClauseConditions(matchMode: Int) {
        when(matchMode) {
            0 -> {
                this.conditions.add("POST_CATEGORY PC ON P.id = PC.post_id")
                this.conditions.add("CATEGORY C ON C.id = PC.category_id")
            }
            1 -> {
                this.conditions.add("POST_CATEGORY PC ON C.id = PC.category_id")
                this.conditions.add("PUBLICATION_POST P ON P.id = PC.post_id")
            }
            2 -> {
                this.conditions.add("POST_CATEGORY PC ON C.id = PC.category_id")
                this.conditions.add("PROMOTION_POST P ON P.id = PC.post_id")
            }
        }
    }

    private fun loadClauseConditions(model: Post?) {
        this.conditions = mutableListOf<String>()
        if(model == null) return
        model.id.takeIf { SH_Formatter.isValidCondition(it) }?.let {
            this.conditions.add("id = $it")
            return
        }
        model.status.takeIf { SH_Formatter.isValidCondition(it) }?.let {
            this.conditions.add("status = ${SH_Formatter.toSqlString(it)}")
        }
        model.publishedInstant.takeIf { SH_Formatter.isValidCondition(it) }?.let {
            this.conditions.add("published_instant >= ${SH_Formatter.toSqlString(it)}")
        }
        when(model) {
            is PublicationPost -> {
                model.discount.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("discount >= ${"%.2f".format(it)}")
                }
                model.title.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("title = ${SH_Formatter.toSqlString(it,2)}")
                }
                model.authorType.takeIf { SH_Formatter.isValidCondition(it) }?.let { authorType ->
                    model.authorId.takeIf { SH_Formatter.isValidCondition(it) }?.let { authorId ->
                        this.conditions.add("author_type = ${authorType} AND author_id = ${authorId}")
                    }
                }
            }
            is PromotionPost -> {
                model.authorId.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("author_id = $it")
                }
                model.startInstant.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("start_instant >= ${SH_Formatter.toSqlString(it)}")
                }
                model.endInstant.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("end_instant >= ${SH_Formatter.toSqlString(it)}")
                }
            }
        }
    }

    private fun loadClauseConditions(model: Category?) {
        this.conditions = mutableListOf<String>()
        if(model == null) return
        model.id.takeIf { SH_Formatter.isValidCondition(it) }?.let {
            this.conditions.add("id = $it")
            return
        }
        model.name.takeIf { SH_Formatter.isValidCondition(it) }?.let {
            this.conditions.add("name LIKE ${SH_Formatter.toSqlString(it,1)}")
        }
    }
}
