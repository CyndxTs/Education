package dev.sportinghub.mobile.model.posts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "PROMOTION_POST")
data class PromotionPost(
    @PrimaryKey(autoGenerate = true)
    override val id: Int? = null,
    @ColumnInfo(name = "published_instant",defaultValue = "CURRENT_TIMESTAMP")
    override var publishedInstant: LocalDateTime? = null,
    @ColumnInfo(name = "status")
    override var status: PostStatus? = null,
    @ColumnInfo(name = "banner_url")
    var bannerUrl: String? = null,
    @ColumnInfo(name = "required_units", defaultValue = "1")
    var requiredUnits: Int = -1,
    @ColumnInfo(name = "paid_units", defaultValue = "1")
    var paidUnits: Int = -1,
    @ColumnInfo(name = "discount_on_paid_units", defaultValue = "0.0")
    var discountOnPaidUnits: Float = -1f,
    @ColumnInfo(name = "start_instant", defaultValue = "CURRENT_TIMESTAMP")
    var startInstant: LocalDateTime? = null,
    @ColumnInfo(name = "end_instant")
    var endInstant: LocalDateTime? = null,
    @ColumnInfo(name = "author_id", defaultValue = "-1")
    var authorId: Int = -1
) : Post {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PromotionPost) return false
        return this.id == other.id
    }

    override fun hashCode(): Int {
        return this.id.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }
}
