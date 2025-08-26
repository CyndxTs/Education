package dev.sportinghub.mobile.repository.posts

import androidx.sqlite.db.SimpleSQLiteQuery
import dev.sportinghub.mobile.client.features.SH_Formatter
import dev.sportinghub.mobile.model.posts.Category
import dev.sportinghub.mobile.room.posts.CategoryDatabaseDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val categoryDatabaseDAO: CategoryDatabaseDAO){
    private val tableName = "CATEGORY"
    private var conditions = mutableListOf<String>()

    suspend fun insert(address: Category): Long = categoryDatabaseDAO.insert(address)

    suspend fun update(address: Category) = categoryDatabaseDAO.update(address)

    suspend fun delete(address: Category) = categoryDatabaseDAO.delete(address)

    fun getByUniqueFields(model: Category): Flow<Category> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model,1)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return categoryDatabaseDAO.getByUniqueFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    fun getAllByFields(model: Category?): Flow<Category> {
        var sql = "SELECT * FROM ${this.tableName}"
        loadClauseConditions(model)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return categoryDatabaseDAO.getAllByFields(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    private fun loadClauseConditions(model: Category?,matchMode: Int = 0) {
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
