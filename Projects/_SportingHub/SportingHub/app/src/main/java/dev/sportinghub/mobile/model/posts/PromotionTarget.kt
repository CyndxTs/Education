package dev.sportinghub.mobile.model.posts

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "PROMOTION_TARGET",
    primaryKeys = ["promotion_id","target_type", "target_id"]
)
data class PromotionTarget(
    @ColumnInfo(name = "promotion_id")
    val promotionId: Int,
    @ColumnInfo(name = "target_type")
    val targetType: TargetType,
    @ColumnInfo(name = "target_id")
    val targetId: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PromotionTarget) return false
        return this.promotionId == other.promotionId && this.targetType == other.targetType && this.targetId == other.targetId
    }

    override fun hashCode(): Int {
        return return "$promotionId|${targetType.toString().uppercase()}|$targetId".hashCode()
    }
}
