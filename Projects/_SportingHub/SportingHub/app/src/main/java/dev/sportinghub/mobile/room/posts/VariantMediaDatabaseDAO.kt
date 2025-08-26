package dev.sportinghub.mobile.room.posts

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import dev.sportinghub.mobile.model.posts.VariantMedia
import kotlinx.coroutines.flow.Flow

@Dao
interface VariantMediaDatabaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(variantMedia: VariantMedia): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(variantMedia: VariantMedia)

    @Delete
    suspend fun delete(variantMedia: VariantMedia)

    @RawQuery(observedEntities = [VariantMedia::class])
    fun getByUniqueFields(query: SupportSQLiteQuery): Flow<VariantMedia>

    @RawQuery(observedEntities = [VariantMedia::class])
    fun getAllByFields(query: SupportSQLiteQuery): Flow<VariantMedia>
}
