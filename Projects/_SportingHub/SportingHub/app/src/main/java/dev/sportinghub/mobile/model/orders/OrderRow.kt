package dev.sportinghub.mobile.model.orders

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ORDER_ROW")
data class OrderRow(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "subtotal")
    var subtotal: Float = -1f,
    @ColumnInfo(name = "quantity")
    var quantity: Int = -1,
    @ColumnInfo(name = "status")
    var status: OrderRowStatus? = null,
    @ColumnInfo(name = "order_id")
    var orderId: Int = -1,
    @ColumnInfo(name = "provider_id")
    var providerId: Int = -1,
    @ColumnInfo(name = "variant_id")
    var variantId: Int = -1
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OrderRow) return false
        return this.id == other.id
    }

    override fun hashCode(): Int {
        return this.id.hashCode()
    }
}
