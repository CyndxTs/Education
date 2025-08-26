package dev.sportinghub.mobile.room.accounts

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import dev.sportinghub.mobile.model.accounts.Address
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressDatabaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(address: Address): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(address: Address)

    @Delete
    suspend fun delete(address: Address)

    @RawQuery(observedEntities = [Address::class])
    fun getByUniqueFields(query: SupportSQLiteQuery): Flow<Address>

    @RawQuery(observedEntities = [Address::class])
    fun getAllByFields(query: SupportSQLiteQuery): Flow<Address>
}
