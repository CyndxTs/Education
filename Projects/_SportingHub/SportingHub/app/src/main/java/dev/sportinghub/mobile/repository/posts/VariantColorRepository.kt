package dev.sportinghub.mobile.repository.posts

import androidx.sqlite.db.SimpleSQLiteQuery
import dev.sportinghub.mobile.client.features.SH_Formatter
import dev.sportinghub.mobile.model.posts.VariantColor
import dev.sportinghub.mobile.room.posts.VariantColorDatabaseDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class VariantColorRepository @Inject constructor(private val variantColorDatabaseDAO: VariantColorDatabaseDAO){
    private val tableName = "VARIANT_COLOR"
    private var conditions = mutableListOf<String>()

    suspend fun insert(variantColor: VariantColor): Long = variantColorDatabaseDAO.insert(variantColor)

    suspend fun update(variantColor: VariantColor) = variantColorDatabaseDAO.update(variantColor)

    suspend fun delete(variantColor: VariantColor) = variantColorDatabaseDAO.delete(variantColor)

    fun getByUniqueFields(model: VariantColor): Flow<VariantColor> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model,1)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return variantColorDatabaseDAO.getByUniqueFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    fun getById(id: Int): Flow<VariantColor?> {
        val sql = "SELECT * FROM $tableName WHERE id = $id"
        return variantColorDatabaseDAO.getByUniqueFields(SimpleSQLiteQuery(sql))
            .flowOn(Dispatchers.IO)
            .conflate()
    }

    fun getAllByFields(model: VariantColor?): Flow<VariantColor> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return variantColorDatabaseDAO.getAllByFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    private fun loadClauseConditions(model: VariantColor?,matchMode: Int = 0) {
        this.conditions = mutableListOf<String>()
        if(model == null) return
        when(matchMode) {
            0 -> {
                model.name.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("name LIKE ${SH_Formatter.toSqlString(it,1)}")
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
