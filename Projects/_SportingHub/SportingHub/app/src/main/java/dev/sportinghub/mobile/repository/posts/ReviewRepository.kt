package dev.sportinghub.mobile.repository.posts

import androidx.sqlite.db.SimpleSQLiteQuery
import dev.sportinghub.mobile.client.features.SH_Formatter
import dev.sportinghub.mobile.model.posts.Review
import dev.sportinghub.mobile.room.posts.ReviewDatabaseDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ReviewRepository @Inject constructor(private val reviewDatabaseDAO: ReviewDatabaseDAO){
    private val tableName = "REVIEW"
    private var conditions = mutableListOf<String>()

    suspend fun insert(review: Review): Long = reviewDatabaseDAO.insert(review)

    suspend fun update(review: Review) = reviewDatabaseDAO.update(review)

    suspend fun delete(review: Review) = reviewDatabaseDAO.delete(review)

    fun getByUniqueFields(model: Review): Flow<Review> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model,1)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return reviewDatabaseDAO.getByUniqueFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    fun getAllByFields(model: Review?): Flow<Review> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return reviewDatabaseDAO.getAllByFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    private fun loadClauseConditions(model: Review?,matchMode: Int = 0) {
        this.conditions = mutableListOf<String>()
        if(model == null) return
        when(matchMode) {
            0 -> {
                model.publicationId.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("publication_id = $it")
                }
                model.authorId.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("author_id = $it")
                }
                model.rate.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("rate >= ${"%.2f".format(it)}")
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
