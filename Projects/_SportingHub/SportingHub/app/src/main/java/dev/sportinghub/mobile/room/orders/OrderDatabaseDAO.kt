package dev.sportinghub.mobile.room.orders

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import dev.sportinghub.mobile.model.orders.Order
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDatabaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(order: Order): Long
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(order: Order)

    @Delete
    suspend fun delete(order: Order)

    @RawQuery(observedEntities = [Order::class])
    fun getByUniqueFields(query: SupportSQLiteQuery): Flow<Order>

    @RawQuery(observedEntities = [Order::class])
    fun getAllByFields(query: SupportSQLiteQuery): Flow<Order>
}
