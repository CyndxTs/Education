package dev.sportinghub.mobile.repository.posts

import androidx.sqlite.db.SimpleSQLiteQuery
import dev.sportinghub.mobile.client.features.SH_Formatter
import dev.sportinghub.mobile.model.posts.Report
import dev.sportinghub.mobile.room.posts.ReportDatabaseDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ReportRepository @Inject constructor(private val reportDatabaseDAO: ReportDatabaseDAO){
    private val tableName = "REPORT"
    private var conditions = mutableListOf<String>()

    suspend fun insert(report: Report): Long = reportDatabaseDAO.insert(report)

    suspend fun update(report: Report) = reportDatabaseDAO.update(report)

    suspend fun delete(report: Report) = reportDatabaseDAO.delete(report)

    fun getByUniqueFields(model: Report): Flow<Report> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model,1)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return reportDatabaseDAO.getByUniqueFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    fun getAllByFields(model: Report?): Flow<Report> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return reportDatabaseDAO.getAllByFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    private fun loadClauseConditions(model: Report?,matchMode: Int = 0) {
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
                model.title.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("title LIKE ${SH_Formatter.toSqlString(it,2)}")
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
