package dev.sportinghub.mobile.room.accounts


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import dev.sportinghub.mobile.model.accounts.AdminAccount
import kotlinx.coroutines.flow.Flow

@Dao
interface AdminAccountDatabaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: AdminAccount): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(account: AdminAccount)

    @Delete
    suspend fun delete(account: AdminAccount)

    @RawQuery(observedEntities = [AdminAccount::class])
    fun getByUniqueFields(query: SupportSQLiteQuery): Flow<AdminAccount>

    @RawQuery(observedEntities = [AdminAccount::class])
    fun getAllByFields(query: SupportSQLiteQuery): Flow<AdminAccount>
}
