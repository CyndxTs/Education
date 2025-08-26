package dev.sportinghub.mobile.repository.orders

import androidx.sqlite.db.SimpleSQLiteQuery
import dev.sportinghub.mobile.client.features.SH_Formatter
import dev.sportinghub.mobile.model.orders.Order
import dev.sportinghub.mobile.room.orders.OrderDatabaseDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class OrderRepository @Inject constructor(private val orderDatabaseDAO: OrderDatabaseDAO){
    private val tableName = "ORDER"
    private var conditions = mutableListOf<String>()

    suspend fun insert(address: Order): Long = orderDatabaseDAO.insert(address)

    suspend fun update(address: Order) = orderDatabaseDAO.update(address)

    suspend fun delete(address: Order) = orderDatabaseDAO.delete(address)

    fun getByUniqueFields(model: Order): Flow<Order> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model,1)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return orderDatabaseDAO.getByUniqueFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    fun getAllByFields(model: Order? = null): Flow<Order> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return orderDatabaseDAO.getAllByFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    private fun loadClauseConditions(model: Order?,matchMode: Int = 0) {
        this.conditions = mutableListOf<String>()
        if(model == null) return
        when(matchMode) {
            0 -> {
                model.status.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("status = ${SH_Formatter.toSqlString(it)}")
                }
                model.deliveryAddressId.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("delivery_address_id = $it")
                }
                model.currency.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("currency = ${SH_Formatter.toSqlString(it)}")
                }
                model.clientId.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("client_id = $it")
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
