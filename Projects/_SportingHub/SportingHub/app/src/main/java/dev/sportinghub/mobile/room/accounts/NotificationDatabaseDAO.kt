package dev.sportinghub.mobile.room.accounts

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import dev.sportinghub.mobile.model.accounts.Notification
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDatabaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: Notification): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(account: Notification)

    @Delete
    suspend fun delete(account: Notification)

    @RawQuery(observedEntities = [Notification::class])
    fun getByUniqueFields(query: SupportSQLiteQuery): Flow<Notification>

    @RawQuery(observedEntities = [Notification::class])
    fun getAllByFields(query: SupportSQLiteQuery): Flow<Notification>
}
