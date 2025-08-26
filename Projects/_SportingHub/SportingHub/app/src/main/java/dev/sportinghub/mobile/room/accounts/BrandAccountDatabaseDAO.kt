package dev.sportinghub.mobile.room.accounts

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import dev.sportinghub.mobile.model.accounts.BrandAccount
import kotlinx.coroutines.flow.Flow

@Dao
interface BrandAccountDatabaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: BrandAccount): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(account: BrandAccount)

    @Delete
    suspend fun delete(account: BrandAccount)

    @RawQuery(observedEntities = [BrandAccount::class])
    fun getByUniqueFields(query: SupportSQLiteQuery): Flow<BrandAccount>

    @RawQuery(observedEntities = [BrandAccount::class])
    fun getAllByFields(query: SupportSQLiteQuery): Flow<BrandAccount>
}
