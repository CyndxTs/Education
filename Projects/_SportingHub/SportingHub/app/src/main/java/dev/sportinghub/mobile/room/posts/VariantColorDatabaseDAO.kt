package dev.sportinghub.mobile.room.posts

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import dev.sportinghub.mobile.model.posts.VariantColor
import kotlinx.coroutines.flow.Flow

@Dao
interface VariantColorDatabaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(variantColor: VariantColor): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(variantColor: VariantColor)

    @Delete
    suspend fun delete(variantColor: VariantColor)

    @RawQuery(observedEntities = [VariantColor::class])
    fun getByUniqueFields(query: SupportSQLiteQuery): Flow<VariantColor>

    @RawQuery(observedEntities = [VariantColor::class])
    fun getAllByFields(query: SupportSQLiteQuery): Flow<VariantColor>
}
