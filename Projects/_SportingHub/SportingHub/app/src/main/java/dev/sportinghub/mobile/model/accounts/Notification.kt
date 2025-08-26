package dev.sportinghub.mobile.model.accounts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "NOTIFICATION")
data class Notification(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "content")
    val content: String = "",
    @ColumnInfo(name = "is_read")
    val isRead: Boolean = false,
    @ColumnInfo(name = "sent_instant")
    val sentInstant: LocalDateTime? = null,
    @ColumnInfo(name = "type")
    val type: NotificationType? = null,
    @ColumnInfo(name = "target_id")
    val targetId: Int = -1,
    @ColumnInfo(name = "account_type")
    val accountType: AccountType? = null,
    @ColumnInfo(name = "account_id")
    val accountId: Int = -1
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Notification) return false
        return this.id == other.id
    }

    override fun hashCode(): Int {
        return this.id.hashCode()
    }
}
