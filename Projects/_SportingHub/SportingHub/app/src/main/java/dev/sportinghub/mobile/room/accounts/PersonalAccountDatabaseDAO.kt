package dev.sportinghub.mobile.room.accounts

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import dev.sportinghub.mobile.model.accounts.PersonalAccount
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonalAccountDatabaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: PersonalAccount): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(account: PersonalAccount)

    @Delete
    suspend fun delete(account: PersonalAccount)

    @RawQuery(observedEntities = [PersonalAccount::class])
    fun getByUniqueFields(query: SupportSQLiteQuery): Flow<PersonalAccount>

    @RawQuery(observedEntities = [PersonalAccount::class])
    fun getAllByFields(query: SupportSQLiteQuery): Flow<PersonalAccount>
}
