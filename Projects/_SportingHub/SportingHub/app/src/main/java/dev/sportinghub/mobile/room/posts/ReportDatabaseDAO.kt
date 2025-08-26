package dev.sportinghub.mobile.room.posts

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import dev.sportinghub.mobile.model.posts.Report
import kotlinx.coroutines.flow.Flow

@Dao
interface ReportDatabaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(report: Report): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(report: Report)

    @Delete
    suspend fun delete(report: Report)

    @RawQuery(observedEntities = [Report::class])
    fun getByUniqueFields(query: SupportSQLiteQuery): Flow<Report>

    @RawQuery(observedEntities = [Report::class])
    fun getAllByFields(query: SupportSQLiteQuery): Flow<Report>
}
