package dev.sportinghub.mobile.repository.posts

import androidx.sqlite.db.SimpleSQLiteQuery
import dev.sportinghub.mobile.client.features.SH_Formatter
import dev.sportinghub.mobile.model.posts.Category
import dev.sportinghub.mobile.model.posts.PromotionPost
import dev.sportinghub.mobile.model.posts.PromotionTarget
import dev.sportinghub.mobile.model.posts.PublicationPost
import dev.sportinghub.mobile.model.posts.TargetType
import dev.sportinghub.mobile.room.posts.PromotionTargetDatabaseDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PromotionTargetRepository @Inject constructor(private val promotionTargetDatabaseDAO: PromotionTargetDatabaseDAO) {
    private var conditions = mutableListOf<String>()

    suspend fun insert(promotionId: Int,targetType: TargetType,targetId: Int): Long = promotionTargetDatabaseDAO.insert(PromotionTarget(promotionId,targetType,targetId))

    suspend fun delete(promotionId: Int,targetType: TargetType,targetId: Int) = promotionTargetDatabaseDAO.delete(PromotionTarget(promotionId,targetType,targetId))

    fun getCategoriesByPromotionPost(model: PromotionPost?): Flow<Category> {
        var sql = "SELECT * FROM CATEGORY C"
        loadClauseConditions(0)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.JOIN)
        loadClauseConditions(model)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return promotionTargetDatabaseDAO.getCategoriesByPromotionPost(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    fun getPublicationPostsByPromotionPost(model: PromotionPost?): Flow<PublicationPost> {
        var sql = "SELECT * FROM PUBLICATION_POST PUP"
        loadClauseConditions(1)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.JOIN)
        loadClauseConditions(model)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return promotionTargetDatabaseDAO.getPublicationPostsByPromotionPost(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    private fun loadClauseConditions(matchMode: Int) {
        when(matchMode) {
            0 -> {
                this.conditions.add("PROMOTION_TARGET PT ON C.id = PT.category_id")
                this.conditions.add("PROMOTION_POST PRP ON PRP.id = PT.post_id")
            }
            1 -> {
                this.conditions.add("PROMOTION_TARGET PT ON PUP.id = PT.category_id")
                this.conditions.add("PROMOTION_POST PRP ON PRP.id = PT.post_id")
            }
        }
    }

    private fun loadClauseConditions(model: PromotionPost?) {
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
