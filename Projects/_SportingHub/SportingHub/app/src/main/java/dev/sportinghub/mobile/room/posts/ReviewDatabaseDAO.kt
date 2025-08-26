package dev.sportinghub.mobile.room.posts

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import dev.sportinghub.mobile.model.posts.Review
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDatabaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(review: Review): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(review: Review)

    @Delete
    suspend fun delete(review: Review)

    @RawQuery(observedEntities = [Review::class])
    fun getByUniqueFields(query: SupportSQLiteQuery): Flow<Review>

    @RawQuery(observedEntities = [Review::class])
    fun getAllByFields(query: SupportSQLiteQuery): Flow<Review>
}
