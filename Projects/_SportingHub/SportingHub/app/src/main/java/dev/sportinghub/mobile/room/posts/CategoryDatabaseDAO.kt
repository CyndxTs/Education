package dev.sportinghub.mobile.room.posts

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import dev.sportinghub.mobile.model.posts.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDatabaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: Category): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)

    @RawQuery(observedEntities = [Category::class])
    fun getByUniqueFields(query: SupportSQLiteQuery): Flow<Category>

    @RawQuery(observedEntities = [Category::class])
    fun getAllByFields(query: SupportSQLiteQuery): Flow<Category>
}
