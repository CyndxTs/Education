package dev.sportinghub.mobile.model.accounts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ADMIN_ACCOUNT")
data class AdminAccount(
    @PrimaryKey(autoGenerate = true)
    override val id: Int? = null,
    @ColumnInfo(name = "email")
    override var email: String = "",
    @ColumnInfo(name = "nickname")
    override var nickname: String = "",
    @ColumnInfo(name = "password")
    override var password: String = "",
    @ColumnInfo(name = "status", defaultValue = "OFFLINE")
    override var status: AccountStatus? = null,
    @ColumnInfo(name = "phone_number")
    var phoneNumber: String = "",
    @ColumnInfo(name = "bio")
    override var bio: String? = null,
    @ColumnInfo(name = "photo_url")
    override var photoUrl: String? = null
): Account {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AdminAccount) return false
        return this.email == other.email || this.nickname == other.nickname
    }

    override fun hashCode(): Int {
        return "$email${email.length}|$nickname${nickname.length}".hashCode()
    }

    override fun toString(): String {
        return ">> ADMIN : '${this.phoneNumber}'"
    }
}
