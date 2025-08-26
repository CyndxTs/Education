package dev.sportinghub.mobile.room.posts

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import dev.sportinghub.mobile.model.posts.Category
import dev.sportinghub.mobile.model.posts.PromotionPost
import dev.sportinghub.mobile.model.posts.PromotionTarget
import dev.sportinghub.mobile.model.posts.PublicationPost
import kotlinx.coroutines.flow.Flow

@Dao
interface PromotionTargetDatabaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(promotionTarget: PromotionTarget): Long

    @Delete
    suspend fun delete(promotionTarget: PromotionTarget)

    @RawQuery(observedEntities = [PromotionPost::class,Category::class])
    fun getCategoriesByPromotionPost(query: SupportSQLiteQuery): Flow<Category>

    @RawQuery(observedEntities = [PromotionPost::class,PublicationPost::class])
    fun getPublicationPostsByPromotionPost(query: SupportSQLiteQuery): Flow<PublicationPost>
}

