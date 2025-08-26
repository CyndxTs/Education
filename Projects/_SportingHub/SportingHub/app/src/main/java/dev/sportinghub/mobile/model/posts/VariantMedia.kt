package dev.sportinghub.mobile.model.posts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "VARIANT_MEDIA")
data class VariantMedia(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "position", defaultValue = "1")
    var position: Int = -1,
    @ColumnInfo(name = "media_url")
    var mediaUrl: String = "",
    @ColumnInfo(name = "variant_id")
    var variantId: Int = -1
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VariantMedia) return false
        return this.id == other.id
    }

    override fun hashCode(): Int {
        return this.id.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }
}
