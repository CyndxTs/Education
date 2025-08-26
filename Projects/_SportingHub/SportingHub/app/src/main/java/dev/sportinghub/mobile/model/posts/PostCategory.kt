package dev.sportinghub.mobile.model.posts

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "POST_CATEGORY",
    primaryKeys = ["post_type","post_id", "category_id"]
)
data class PostCategory(
    @ColumnInfo(name = "post_type")
    val postType: PostType,
    @ColumnInfo(name = "post_id")
    val postId: Int,
    @ColumnInfo(name = "category_id")
    val categoryId: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PostCategory) return false
        return this.postId == other.postId && this.categoryId == other.categoryId
    }

    override fun hashCode(): Int {
        return "${postType.toString().uppercase()}|$postId|$categoryId".hashCode()
    }
}
