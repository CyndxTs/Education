package dev.sportinghub.mobile.room.posts

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import dev.sportinghub.mobile.model.posts.Variant
import kotlinx.coroutines.flow.Flow

@Dao
interface VariantDatabaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(variant: Variant): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(variant: Variant)

    @Delete
    suspend fun delete(variant: Variant)

    @RawQuery(observedEntities = [Variant::class])
    fun getByUniqueFields(query: SupportSQLiteQuery): Flow<Variant>

    @RawQuery(observedEntities = [Variant::class])
    fun getAllByFields(query: SupportSQLiteQuery): Flow<Variant>
}
