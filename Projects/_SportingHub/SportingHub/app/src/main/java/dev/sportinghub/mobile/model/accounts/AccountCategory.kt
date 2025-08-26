package dev.sportinghub.mobile.model.accounts

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "ACCOUNT_CATEGORY",
    primaryKeys = ["account_type","account_id", "category_id"]
)
data class AccountCategory(
    @ColumnInfo(name = "account_type")
    val accountType: AccountType,
    @ColumnInfo(name = "account_id")
    val accountId: Int,
    @ColumnInfo(name = "category_id")
    val categoryId: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AccountCategory) return false
        return this.accountId == other.accountId && this.categoryId == other.categoryId
    }

    override fun hashCode(): Int {
        return "${accountType.toString().uppercase()}|$accountId|$categoryId".hashCode()
    }
}
