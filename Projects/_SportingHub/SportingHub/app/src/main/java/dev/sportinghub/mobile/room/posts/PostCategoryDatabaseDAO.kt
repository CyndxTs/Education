package dev.sportinghub.mobile.room.posts

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import dev.sportinghub.mobile.model.posts.Category
import dev.sportinghub.mobile.model.posts.PostCategory
import dev.sportinghub.mobile.model.posts.PromotionPost
import dev.sportinghub.mobile.model.posts.PublicationPost
import kotlinx.coroutines.flow.Flow

@Dao
interface PostCategoryDatabaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(postCategory: PostCategory): Long

    @Delete
    suspend fun delete(postCategory: PostCategory)

    @RawQuery(observedEntities = [PublicationPost::class,Category::class])
    fun getPublicationPostsByCategory(query: SupportSQLiteQuery): Flow<PublicationPost>

    @RawQuery(observedEntities = [PromotionPost::class,Category::class])
    fun getPromotionPostsByCategory(query: SupportSQLiteQuery): Flow<PromotionPost>

    @RawQuery(observedEntities = [PromotionPost::class,PublicationPost::class,Category::class])
    fun getCategoriesByPost(query: SupportSQLiteQuery): Flow<Category>
}
