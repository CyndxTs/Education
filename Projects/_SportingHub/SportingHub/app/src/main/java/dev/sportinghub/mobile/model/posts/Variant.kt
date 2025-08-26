package dev.sportinghub.mobile.model.posts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "VARIANT")
data class Variant(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "price")
    var price: Float = -1f,
    @ColumnInfo(name = "publication_id")
    val publicationId: Int = -1,
    @ColumnInfo(name = "size_id")
    var sizeId: Int? = null,
    @ColumnInfo(name = "color_id")
    var colorId: Int? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Variant) return false
        return this.id == other.id
    }

    override fun hashCode(): Int {
        return this.id.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }
}