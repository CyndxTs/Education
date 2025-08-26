package dev.sportinghub.mobile.repository.accounts

import androidx.sqlite.db.SimpleSQLiteQuery
import dev.sportinghub.mobile.client.features.SH_Formatter
import dev.sportinghub.mobile.model.accounts.Notification
import dev.sportinghub.mobile.room.accounts.NotificationDatabaseDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NotificationRepository @Inject constructor(private val notificationDatabaseDAO: NotificationDatabaseDAO){
    private val tableName = "NOTIFICATION"
    private var conditions = mutableListOf<String>()

    suspend fun insert(notification: Notification): Long = notificationDatabaseDAO.insert(notification)

    suspend fun update(notification: Notification) = notificationDatabaseDAO.update(notification)

    suspend fun delete(notification: Notification) = notificationDatabaseDAO.delete(notification)

    fun getByUniqueFields(model: Notification): Flow<Notification> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model,1)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return notificationDatabaseDAO.getByUniqueFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    fun getAllByFields(model: Notification?): Flow<Notification> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return notificationDatabaseDAO.getAllByFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    private fun loadClauseConditions(model: Notification?,matchMode: Int = 0) {
        this.conditions = mutableListOf<String>()
        if(model == null) return
        when(matchMode) {
            0 -> {
                model.isRead.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("is_read = ${SH_Formatter.toSqlString(it)}")
                }
                model.type.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("type = ${SH_Formatter.toSqlString(it)}")
                }
                model.accountId.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("account_id = $it")
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
