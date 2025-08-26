package dev.sportinghub.mobile.room.posts

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import dev.sportinghub.mobile.model.posts.PromotionPost
import kotlinx.coroutines.flow.Flow

@Dao
interface PromotionPostDatabaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(promotionPost: PromotionPost): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(promotionPost: PromotionPost)

    @Delete
    suspend fun delete(promotionPost: PromotionPost)

    @RawQuery(observedEntities = [PromotionPost::class])
    fun getByUniqueFields(query: SupportSQLiteQuery): Flow<PromotionPost>

    @RawQuery(observedEntities = [PromotionPost::class])
    fun getAllByFields(query: SupportSQLiteQuery): List<PromotionPost>
}
