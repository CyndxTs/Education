package dev.sportinghub.mobile.repository.accounts

import androidx.sqlite.db.SimpleSQLiteQuery
import dev.sportinghub.mobile.client.features.SH_Formatter
import dev.sportinghub.mobile.model.accounts.Address
import dev.sportinghub.mobile.room.accounts.AddressDatabaseDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AddressRepository @Inject constructor(private val addressDatabaseDAO: AddressDatabaseDAO){
    private val tableName = "ADDRESS"
    private var conditions = mutableListOf<String>()

    suspend fun insert(address: Address): Long = addressDatabaseDAO.insert(address)

    suspend fun update(address: Address) = addressDatabaseDAO.update(address)

    suspend fun delete(address: Address) = addressDatabaseDAO.delete(address)

    fun getByUniqueFields(model: Address): Flow<Address> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model,1)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return addressDatabaseDAO.getByUniqueFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    fun getAllByFields(model: Address?): Flow<Address> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return addressDatabaseDAO.getAllByFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    private fun loadClauseConditions(model: Address?,matchMode: Int = 0) {
        this.conditions = mutableListOf<String>()
        if(model == null) return
        when(matchMode) {
            0 -> {
                model.country.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("country LIKE ${SH_Formatter.toSqlString(it,2)}")
                }
                model.accountType.takeIf { SH_Formatter.isValidCondition(it) }?.let { accountType ->
                    model.accountId.takeIf { SH_Formatter.isValidCondition(it) }?.let { accountId ->
                        this.conditions.add("account_type = ${SH_Formatter.toSqlString(accountType)} AND account_id = ${accountId}")
                    }
                }
                model.state.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("state LIKE ${SH_Formatter.toSqlString(it,2)}")
                }
                model.city.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("city LIKE ${SH_Formatter.toSqlString(it,2)}")
                }
                model.postalCode.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("postal_code = ${SH_Formatter.toSqlString(it)}")
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
