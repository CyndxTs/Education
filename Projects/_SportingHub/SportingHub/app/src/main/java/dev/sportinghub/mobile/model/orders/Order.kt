package dev.sportinghub.mobile.model.orders

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ORDER")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "total")
    var total: Float = 0f,
    @ColumnInfo(name = "currency")
    var currency: String = "",
    @ColumnInfo(name = "status")
    var status: OrderStatus? = null,
    @ColumnInfo(name = "client_id")
    var clientId: Int = -1,
    @ColumnInfo(name = "delivery_address_id")
    var deliveryAddressId: Int = -1
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Order) return false
        return this.id == other.id
    }

    override fun hashCode(): Int {
        return this.id.hashCode()
    }
}