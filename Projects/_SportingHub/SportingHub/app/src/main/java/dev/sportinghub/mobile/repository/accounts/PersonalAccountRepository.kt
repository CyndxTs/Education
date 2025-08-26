package dev.sportinghub.mobile.repository.accounts

import androidx.sqlite.db.SimpleSQLiteQuery
import dev.sportinghub.mobile.client.features.SH_Formatter
import dev.sportinghub.mobile.model.accounts.PersonalAccount
import dev.sportinghub.mobile.room.accounts.PersonalAccountDatabaseDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PersonalAccountRepository @Inject constructor(private val personalAccountDatabaseDAO: PersonalAccountDatabaseDAO){
    private val tableName = "PERSONAL_ACCOUNT"
    private var conditions = mutableListOf<String>()

    suspend fun insert(account: PersonalAccount): Long = personalAccountDatabaseDAO.insert(account)

    suspend fun update(account: PersonalAccount) = personalAccountDatabaseDAO.update(account)

    suspend fun delete(account: PersonalAccount) = personalAccountDatabaseDAO.delete(account)

    fun getByUniqueFields(model: PersonalAccount): Flow<PersonalAccount> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model,1)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return personalAccountDatabaseDAO.getByUniqueFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    fun getAllByFields(model: PersonalAccount? = null): Flow<PersonalAccount> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return personalAccountDatabaseDAO.getAllByFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    private fun loadClauseConditions(model: PersonalAccount?,matchMode: Int = 0) {
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
                model.firstName.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("first_name LIKE ${SH_Formatter.toSqlString(it,2)}")
                }
                model.lastName.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("last_name LIKE ${SH_Formatter.toSqlString(it,2)}")
                }
                model.birthDate.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("birthdate > ${SH_Formatter.toSqlString(it)}")
                }
                model.gender.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("gender LIKE ${SH_Formatter.toSqlString(it,2)}")
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
