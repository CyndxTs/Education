package dev.sportinghub.mobile.repository.posts

import androidx.sqlite.db.SimpleSQLiteQuery
import dev.sportinghub.mobile.client.features.SH_Formatter
import dev.sportinghub.mobile.model.posts.VariantSize
import dev.sportinghub.mobile.room.posts.VariantSizeDatabaseDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class VariantSizeRepository @Inject constructor(private val variantSizeDatabaseDAO: VariantSizeDatabaseDAO){
    private val tableName = "VARIANT_SIZE"
    private var conditions = mutableListOf<String>()

    suspend fun insert(variantSize: VariantSize): Long = variantSizeDatabaseDAO.insert(variantSize)

    suspend fun update(variantSize: VariantSize) = variantSizeDatabaseDAO.update(variantSize)

    suspend fun delete(variantSize: VariantSize) = variantSizeDatabaseDAO.delete(variantSize)

    fun getByUniqueFields(model: VariantSize): Flow<VariantSize> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model,1)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return variantSizeDatabaseDAO.getByUniqueFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    fun getAllByFields(model: VariantSize?): Flow<VariantSize> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return variantSizeDatabaseDAO.getAllByFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    private fun loadClauseConditions(model: VariantSize?,matchMode: Int = 0) {
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
