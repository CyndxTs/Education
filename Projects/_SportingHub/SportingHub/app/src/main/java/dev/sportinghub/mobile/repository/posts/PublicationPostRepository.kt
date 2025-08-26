package dev.sportinghub.mobile.repository.posts

import androidx.sqlite.db.SimpleSQLiteQuery
import dev.sportinghub.mobile.client.features.SH_Formatter
import dev.sportinghub.mobile.model.posts.PublicationPost
import dev.sportinghub.mobile.room.posts.PublicationPostDatabaseDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PublicationPostRepository @Inject constructor(private val publicationPostDatabaseDAO: PublicationPostDatabaseDAO){
    private val tableName = "PUBLICATION_POST"
    private var conditions = mutableListOf<String>()

    suspend fun insert(publicationPost: PublicationPost): Long = publicationPostDatabaseDAO.insert(publicationPost)

    suspend fun update(publicationPost: PublicationPost) = publicationPostDatabaseDAO.update(publicationPost)

    suspend fun delete(publicationPost: PublicationPost) = publicationPostDatabaseDAO.delete(publicationPost)

    fun getByUniqueFields(model: PublicationPost): Flow<PublicationPost> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model,1)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return publicationPostDatabaseDAO.getByUniqueFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    suspend fun getAllByFields(model: PublicationPost?): List<PublicationPost> = withContext(Dispatchers.IO) {
        var sql = "SELECT * FROM ${tableName}"
        loadClauseConditions(model)
        sql = SH_Formatter.appendClause(sql,conditions,SH_Formatter.Clause.WHERE)
        publicationPostDatabaseDAO.getAllByFields(SimpleSQLiteQuery(sql))
    }


    private fun loadClauseConditions(model: PublicationPost?,matchMode: Int = 0) {
        this.conditions = mutableListOf<String>()
        if(model == null) return
        when(matchMode) {
            0 -> {
                model.status.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("status = ${SH_Formatter.toSqlString(it)}")
                }
                model.discount.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("discount >= ${"%.2f".format(it)}")
                }
                model.title.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("title = ${SH_Formatter.toSqlString(it,2)}")
                }
                model.publishedInstant.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("published_instant >= ${SH_Formatter.toSqlString(it)}")
                }
                model.authorType.takeIf { SH_Formatter.isValidCondition(it) }?.let { authorType ->
                    model.authorId.takeIf { SH_Formatter.isValidCondition(it) }?.let { authorId ->
                        this.conditions.add("author_type = ${authorType} AND author_id = ${authorId}")
                    }
                }
            }
            1 -> {
                model.id.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("id = $it")
                }
            }
        }
    }
}
