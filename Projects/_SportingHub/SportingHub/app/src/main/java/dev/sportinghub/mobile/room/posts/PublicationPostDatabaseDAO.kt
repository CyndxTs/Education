package dev.sportinghub.mobile.room.posts

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import dev.sportinghub.mobile.model.posts.PublicationPost
import kotlinx.coroutines.flow.Flow

@Dao
interface PublicationPostDatabaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(publicationPost: PublicationPost): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(publicationPost: PublicationPost)

    @Delete
    suspend fun delete(publicationPost: PublicationPost)

    @RawQuery(observedEntities = [PublicationPost::class])
    fun getByUniqueFields(query: SupportSQLiteQuery): Flow<PublicationPost>

    @RawQuery(observedEntities = [PublicationPost::class])
    fun getAllByFields(query: SupportSQLiteQuery): List<PublicationPost>
}
