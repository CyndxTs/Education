package dev.sportinghub.mobile.model.posts

import java.time.LocalDateTime

interface Post {
    val id: Int?
    var publishedInstant: LocalDateTime?
    var status: PostStatus?
}
