package dev.sportinghub.mobile.model.posts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.sportinghub.mobile.model.accounts.AccountType
import java.time.LocalDateTime


@Entity(tableName = "PUBLICATION_POST")
data class PublicationPost(
    @PrimaryKey(autoGenerate = true)
    override val id: Int? = null,
    @ColumnInfo(name = "published_instant", defaultValue = "CURRENT_TIMESTAMP")
    override var publishedInstant: LocalDateTime? = null,
    @ColumnInfo(name = "status", defaultValue = "PROCESSING")
    override var status: PostStatus? = null,
    @ColumnInfo(name = "title")
    var title: String = "",
    @ColumnInfo(name = "description")
    var description: String? = null,
    @ColumnInfo(name = "discount")
    var discount: Float? = null,
    @ColumnInfo(name = "author_type")
    var authorType: AccountType? = null,
    @ColumnInfo(name = "author_id")
    var authorId: Int = -1
): Post {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PublicationPost) return false
        return this.id == other.id
    }

    override fun hashCode(): Int {
        return this.id.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }
}