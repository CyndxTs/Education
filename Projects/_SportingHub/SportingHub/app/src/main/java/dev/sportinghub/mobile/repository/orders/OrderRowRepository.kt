package dev.sportinghub.mobile.repository.orders

import androidx.sqlite.db.SimpleSQLiteQuery
import dev.sportinghub.mobile.client.features.SH_Formatter
import dev.sportinghub.mobile.model.orders.OrderRow
import dev.sportinghub.mobile.room.orders.OrderRowDatabaseDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class OrderRowRepository @Inject constructor(private val orderRowDatabaseDAO: OrderRowDatabaseDAO){
    private val tableName = "ORDER_ROW"
    private var conditions = mutableListOf<String>()

    suspend fun insert(orderRow: OrderRow): Long = orderRowDatabaseDAO.insert(orderRow)

    suspend fun update(orderRow: OrderRow) = orderRowDatabaseDAO.update(orderRow)

    suspend fun delete(orderRow: OrderRow) = orderRowDatabaseDAO.delete(orderRow)

    fun getByUniqueFields(model: OrderRow): Flow<OrderRow> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model,1)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return orderRowDatabaseDAO.getByUniqueFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    fun getAllByFields(model: OrderRow? = null): Flow<OrderRow> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return orderRowDatabaseDAO.getAllByFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    private fun loadClauseConditions(model: OrderRow?,matchMode: Int = 0) {
        this.conditions = mutableListOf<String>()
        if(model == null) return
        when(matchMode) {
            0 -> {
                model.status.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("status = ${SH_Formatter.toSqlString(it)}")
                }
                model.orderId.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("order_id = $it")
                }
                model.providerId.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("provider_id = $it")
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
