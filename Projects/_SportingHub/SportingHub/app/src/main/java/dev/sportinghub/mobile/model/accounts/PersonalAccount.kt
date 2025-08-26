package dev.sportinghub.mobile.model.accounts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.sportinghub.mobile.client.features.SH_Formatter
import java.util.Date

@Entity(tableName = "PERSONAL_ACCOUNT")
data class PersonalAccount(
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
    @ColumnInfo(name = "bio")
    override var bio: String? = null,
    @ColumnInfo(name = "photo_url")
    override var photoUrl: String? = null,
    @ColumnInfo(name = "first_name")
    var firstName: String = "",
    @ColumnInfo(name = "last_name")
    var lastName: String = "",
    @ColumnInfo(name = "birth_date")
    var birthDate: Date? = null,
    @ColumnInfo(name = "gender")
    var gender: String? = null
): Account {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PersonalAccount) return false
        return this.email == other.email || this.nickname == other.nickname
    }

    override fun hashCode(): Int {
        return "$email${email.length}|$nickname${nickname.length}".hashCode()
    }

    override fun toString(): String {
        return ">> PERSONAL : '${this.firstName + this.lastName}' ['${this.gender}'] ('${SH_Formatter.toDisplayString(this.birthDate)}')"
    }
}
