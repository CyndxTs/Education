package dev.sportinghub.mobile.repository.posts

import androidx.sqlite.db.SimpleSQLiteQuery
import dev.sportinghub.mobile.client.features.SH_Formatter
import dev.sportinghub.mobile.model.posts.Variant
import dev.sportinghub.mobile.room.posts.VariantDatabaseDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class VariantRepository @Inject constructor(private val variantDatabaseDAO: VariantDatabaseDAO){
    private val tableName = "VARIANT"
    private var conditions = mutableListOf<String>()

    suspend fun insert(variant: Variant): Long = variantDatabaseDAO.insert(variant)

    suspend fun update(variant: Variant) = variantDatabaseDAO.update(variant)

    suspend fun delete(variant: Variant) = variantDatabaseDAO.delete(variant)

    fun getByUniqueFields(model: Variant): Flow<Variant> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model,1)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return variantDatabaseDAO.getByUniqueFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    fun getAllByFields(model: Variant?): Flow<Variant> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return variantDatabaseDAO.getAllByFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    private fun loadClauseConditions(model: Variant?,matchMode: Int = 0) {
        this.conditions = mutableListOf<String>()
        if(model == null) return
        when(matchMode) {
            0 -> {
                model.publicationId.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("publication_id = $it")
                }
                model.colorId.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("color_id = $it")
                }
                model.sizeId.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("size_id = $it")
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
