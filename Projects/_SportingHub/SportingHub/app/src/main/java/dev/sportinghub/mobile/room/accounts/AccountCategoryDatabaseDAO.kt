package dev.sportinghub.mobile.room.accounts

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import dev.sportinghub.mobile.model.accounts.AccountCategory
import dev.sportinghub.mobile.model.accounts.BrandAccount
import dev.sportinghub.mobile.model.accounts.PersonalAccount
import dev.sportinghub.mobile.model.posts.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountCategoryDatabaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(accountCategory: AccountCategory): Long

    @Delete
    suspend fun delete(accountCategory: AccountCategory)

    @RawQuery(observedEntities = [PersonalAccount::class,Category::class])
    fun getPersonalAccountsByCategory(query: SupportSQLiteQuery): Flow<PersonalAccount>

    @RawQuery(observedEntities = [BrandAccount::class,Category::class])
    fun getBrandAccountsByCategory(query: SupportSQLiteQuery): Flow<BrandAccount>

    @RawQuery(observedEntities = [PersonalAccount::class,BrandAccount::class,Category::class])
    fun getCategoriesByAccount(query: SupportSQLiteQuery): Flow<Category>
}
