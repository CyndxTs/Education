package dev.sportinghub.mobile.room.posts

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import dev.sportinghub.mobile.model.posts.VariantSize
import kotlinx.coroutines.flow.Flow

@Dao
interface VariantSizeDatabaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(variantSize: VariantSize): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(variantSize: VariantSize)

    @Delete
    suspend fun delete(variantSize: VariantSize)

    @RawQuery(observedEntities = [VariantSize::class])
    fun getByUniqueFields(query: SupportSQLiteQuery): Flow<VariantSize>

    @RawQuery(observedEntities = [VariantSize::class])
    fun getAllByFields(query: SupportSQLiteQuery): Flow<VariantSize>
}
