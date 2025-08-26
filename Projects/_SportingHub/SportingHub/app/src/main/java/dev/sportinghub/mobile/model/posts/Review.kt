package dev.sportinghub.mobile.model.posts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "REVIEW")
data class Review(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "rate", defaultValue = "5.0")
    var rate: Float = -1f,
    @ColumnInfo(name = "description")
    var description: String? = null,
    @ColumnInfo(name = "author_id")
    var authorId: Int = -1,
    @ColumnInfo(name = "publication_id")
    var publicationId: Int = -1
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Review) return false
        return this.id == other.id
    }

    override fun hashCode(): Int {
        return this.id.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }
}
