package dev.sportinghub.mobile.model.posts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "VARIANT_COLOR")
data class VariantColor(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "name")
    var name: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VariantColor) return false
        return this.id == other.id
    }

    override fun hashCode(): Int {
        return this.id.hashCode()
    }
}
