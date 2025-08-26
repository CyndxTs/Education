package dev.sportinghub.mobile.repository.posts

import androidx.sqlite.db.SimpleSQLiteQuery
import dev.sportinghub.mobile.client.features.SH_Formatter
import dev.sportinghub.mobile.model.posts.VariantMedia
import dev.sportinghub.mobile.room.posts.VariantMediaDatabaseDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class VariantMediaRepository @Inject constructor(private val variantMediaDatabaseDAO: VariantMediaDatabaseDAO){
    private val tableName = "VARIANT_MEDIA"
    private var conditions = mutableListOf<String>()

    suspend fun insert(variantMedia: VariantMedia): Long = variantMediaDatabaseDAO.insert(variantMedia)

    suspend fun update(variantMedia: VariantMedia) = variantMediaDatabaseDAO.update(variantMedia)

    suspend fun delete(variantMedia: VariantMedia) = variantMediaDatabaseDAO.delete(variantMedia)

    fun getByUniqueFields(model: VariantMedia): Flow<VariantMedia> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model,1)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return variantMediaDatabaseDAO.getByUniqueFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    fun getAllByFields(model: VariantMedia?): Flow<VariantMedia> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return variantMediaDatabaseDAO.getAllByFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    private fun loadClauseConditions(model: VariantMedia?,matchMode: Int = 0) {
        this.conditions = mutableListOf<String>()
        if(model == null) return
        when(matchMode) {
            0 -> {
                model.variantId.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("variant_id = $it")
                }
            }
            1 -> {
                model.id.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("id = $it")
                }
                model.position.takeIf { SH_Formatter.isValidCondition(it) }?.let { position ->
                    model.variantId.takeIf { SH_Formatter.isValidCondition(it) }?.let { variantId ->
                        this.conditions.add("position = ${position} AND variant_id = ${variantId}")
                    }
                }
            }
        }
    }
}
