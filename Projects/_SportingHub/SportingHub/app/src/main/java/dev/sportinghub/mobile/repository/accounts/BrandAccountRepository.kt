package dev.sportinghub.mobile.repository.accounts

import androidx.sqlite.db.SimpleSQLiteQuery
import dev.sportinghub.mobile.client.features.SH_Formatter
import dev.sportinghub.mobile.model.accounts.BrandAccount
import dev.sportinghub.mobile.room.accounts.BrandAccountDatabaseDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BrandAccountRepository @Inject constructor(private val brandAccountDatabaseDAO: BrandAccountDatabaseDAO){
    private val tableName = "BRAND_ACCOUNT"
    private var conditions = mutableListOf<String>()

    suspend fun insert(account: BrandAccount): Long = brandAccountDatabaseDAO.insert(account)

    suspend fun update(account: BrandAccount) = brandAccountDatabaseDAO.update(account)

    suspend fun delete(account: BrandAccount) = brandAccountDatabaseDAO.delete(account)

    fun getByUniqueFields(model: BrandAccount): Flow<BrandAccount> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model,1)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return brandAccountDatabaseDAO.getByUniqueFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    fun getAllByFields(model: BrandAccount? = null): Flow<BrandAccount> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return brandAccountDatabaseDAO.getAllByFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    private fun loadClauseConditions(model: BrandAccount?,matchMode: Int = 0) {
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
                model.businessName.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("business_name LIKE ${SH_Formatter.toSqlString(it,1)}")
                }
                model.country.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("country LIKE ${SH_Formatter.toSqlString(it,1)}")
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
                model.taxNumber.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("tax_number = ${SH_Formatter.toSqlString(it)}")
                }
            }
        }
    }
}
