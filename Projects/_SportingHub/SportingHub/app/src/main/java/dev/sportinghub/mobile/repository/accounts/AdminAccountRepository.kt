package dev.sportinghub.mobile.repository.accounts

import androidx.sqlite.db.SimpleSQLiteQuery
import dev.sportinghub.mobile.client.features.SH_Formatter
import dev.sportinghub.mobile.model.accounts.AdminAccount
import dev.sportinghub.mobile.room.accounts.AdminAccountDatabaseDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AdminAccountRepository @Inject constructor(private val adminAccountDatabaseDAO: AdminAccountDatabaseDAO){
    private val tableName = "ADMIN_ACCOUNT"
    private var conditions = mutableListOf<String>()

    suspend fun insert(account: AdminAccount): Long = adminAccountDatabaseDAO.insert(account)

    suspend fun update(account: AdminAccount) = adminAccountDatabaseDAO.update(account)

    suspend fun delete(account: AdminAccount) = adminAccountDatabaseDAO.delete(account)

    fun getByUniqueFields(model: AdminAccount): Flow<AdminAccount> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model,1)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return adminAccountDatabaseDAO.getByUniqueFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    fun getAllByFields(model: AdminAccount? = null): Flow<AdminAccount> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return adminAccountDatabaseDAO.getAllByFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    private fun loadClauseConditions(model: AdminAccount?,matchMode: Int = 0) {
        this.conditions = mutableListOf<String>()
        if(model == null) return
        when(matchMode) {
            0 -> {
                model.email.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("email LIKE ${SH_Formatter.toSqlString(it,1)}")
                }
                model.nickname.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("nickname LIKE ${SH_Formatter.toSqlString(it,2)}")
                }
                model.status.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("status = ${SH_Formatter.toSqlString(it)}")
                }
                model.phoneNumber.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("phone_number = ${SH_Formatter.toSqlString(it)}")
                }
            }
            1 -> {
                model.id.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("id = $it")
                }
                model.email.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("email = ${SH_Formatter.toSqlString(it)}")
                }
                model.nickname.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("nickname = ${SH_Formatter.toSqlString(it)}")
                }
            }
        }
    }
}
