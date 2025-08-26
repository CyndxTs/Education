package dev.sportinghub.mobile.repository.posts

import androidx.sqlite.db.SimpleSQLiteQuery
import dev.sportinghub.mobile.client.features.SH_Formatter
import dev.sportinghub.mobile.model.posts.PromotionPost
import dev.sportinghub.mobile.room.posts.PromotionPostDatabaseDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PromotionPostRepository @Inject constructor(private val promotionPostDatabaseDAO: PromotionPostDatabaseDAO){
    private val tableName = "PROMOTION_POST"
    private var conditions = mutableListOf<String>()

    suspend fun insert(promotionPost: PromotionPost): Long = promotionPostDatabaseDAO.insert(promotionPost)

    suspend fun update(promotionPost: PromotionPost) = promotionPostDatabaseDAO.update(promotionPost)

    suspend fun delete(promotionPost: PromotionPost) = promotionPostDatabaseDAO.delete(promotionPost)

    fun getByUniqueFields(model: PromotionPost): Flow<PromotionPost> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model,1)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return promotionPostDatabaseDAO.getByUniqueFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    suspend fun getAllByFields(model: PromotionPost?): List<PromotionPost> = withContext(Dispatchers.IO) {
        var sql = "SELECT * FROM ${tableName}"
        loadClauseConditions(model)
        sql = SH_Formatter.appendClause(sql,conditions,SH_Formatter.Clause.WHERE)
        println(sql)
        promotionPostDatabaseDAO.getAllByFields(SimpleSQLiteQuery(sql))
    }

    private fun loadClauseConditions(model: PromotionPost?,matchMode: Int = 0) {
        this.conditions = mutableListOf<String>()
        if(model == null) return
        when(matchMode) {
            0 -> {
                model.status.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("status = ${SH_Formatter.toSqlString(it)}")
                }
                model.authorId.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("author_id = $it")
                }
                model.publishedInstant.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("published_instant >= ${SH_Formatter.toSqlString(it)}")
                }
                model.startInstant.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("start_instant >= ${SH_Formatter.toSqlString(it)}")
                }
                model.endInstant.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("end_instant >= ${SH_Formatter.toSqlString(it)}")
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
