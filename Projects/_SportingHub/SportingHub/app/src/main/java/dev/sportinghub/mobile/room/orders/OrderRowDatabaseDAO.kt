package dev.sportinghub.mobile.room.orders

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import dev.sportinghub.mobile.model.orders.OrderRow
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderRowDatabaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(orderRow: OrderRow): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(orderRow: OrderRow)

    @Delete
    suspend fun delete(orderRow: OrderRow)

    @RawQuery(observedEntities = [OrderRow::class])
    fun getByUniqueFields(query: SupportSQLiteQuery): Flow<OrderRow>

    @RawQuery(observedEntities = [OrderRow::class])
    fun getAllByFields(query: SupportSQLiteQuery): Flow<OrderRow>
}
