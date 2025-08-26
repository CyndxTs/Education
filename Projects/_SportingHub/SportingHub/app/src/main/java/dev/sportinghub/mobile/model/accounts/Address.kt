package dev.sportinghub.mobile.model.accounts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ADDRESS")
data class Address(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "country")
    var country: String = "",
    @ColumnInfo(name = "city")
    var city: String = "",
    @ColumnInfo(name = "specification")
    var specification: String = "",
    @ColumnInfo(name = "state")
    var state: String? = "",
    @ColumnInfo(name = "reference")
    var reference: String? = "",
    @ColumnInfo(name = "latitude")
    var latitude: Double? = null,
    @ColumnInfo(name = "longitude")
    var longitude: Double? = null,
    @ColumnInfo(name = "postal_code")
    var postalCode: String? = null,
    @ColumnInfo(name = "account_type")
    var accountType: AccountType? = null,
    @ColumnInfo(name = "account_id")
    var accountId: Int = -1
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Address) return false
        return this.id == other.id
    }

    override fun hashCode(): Int {
        return this.id.hashCode()
    }
}
