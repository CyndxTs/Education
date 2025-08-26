package dev.sportinghub.mobile.viewmodels

import androidx.compose.ui.geometry.isEmpty
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import dev.sportinghub.mobile.model.posts.Category
import dev.sportinghub.mobile.model.posts.DetailedVariantUiModel
import dev.sportinghub.mobile.model.posts.Post
import dev.sportinghub.mobile.model.posts.PromotionPost
import dev.sportinghub.mobile.model.posts.PublicationDetailUiModel
import dev.sportinghub.mobile.model.posts.PublicationPost
import dev.sportinghub.mobile.model.posts.Report
import dev.sportinghub.mobile.model.posts.Review
import dev.sportinghub.mobile.model.posts.Variant
import dev.sportinghub.mobile.model.posts.VariantColor
import dev.sportinghub.mobile.model.posts.VariantMedia
import dev.sportinghub.mobile.model.posts.VariantSize
import dev.sportinghub.mobile.repository.posts.CategoryRepository
import dev.sportinghub.mobile.repository.posts.PostCategoryRepository
import dev.sportinghub.mobile.repository.posts.PromotionPostRepository
import dev.sportinghub.mobile.repository.posts.PromotionTargetRepository
import dev.sportinghub.mobile.repository.posts.PublicationPostRepository
import dev.sportinghub.mobile.repository.posts.ReportRepository
import dev.sportinghub.mobile.repository.posts.ReviewRepository
import dev.sportinghub.mobile.repository.posts.VariantColorRepository
import dev.sportinghub.mobile.repository.posts.VariantMediaRepository
import dev.sportinghub.mobile.repository.posts.VariantRepository
import dev.sportinghub.mobile.repository.posts.VariantSizeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import android.util.Log


class PostUseCase @Inject constructor(
    val categoryRepository: CategoryRepository,
    val postCategoryRepository: PostCategoryRepository,
    val promotionPostRepository: PromotionPostRepository,
    val promotionTargetRepository: PromotionTargetRepository,
    val publicationPostRepository: PublicationPostRepository,
    val reportRepository: ReportRepository,
    val reviewRepository: ReviewRepository,
    val variantColorRepository: VariantColorRepository,
    val variantMediaRepository: VariantMediaRepository,
    val variantRepository: VariantRepository,
    val variantSizeRepository: VariantSizeRepository
) {
    suspend fun post(post: Post): Boolean {
        return try {
            when(post) {
                is PublicationPost -> publicationPostRepository.insert(post)
                else -> promotionPostRepository.insert(post as PromotionPost)
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun insert(post: Post): Int {
        return when(post) {
            is PublicationPost -> publicationPostRepository.insert(post).toInt()
            else -> promotionPostRepository.insert(post as PromotionPost).toInt()
        }
    }

    suspend fun getPublicationById(id: Int): PublicationPost? {
        return publicationPostRepository.getByUniqueFields(PublicationPost(id = id)).firstOrNull()
    }

    suspend fun getPromotionById(id: Int): PromotionPost? {
        return promotionPostRepository.getByUniqueFields(PromotionPost(id = id)).firstOrNull()
    }
    suspend fun search(filterModel: Post? = null): MutableList<Post> {
        val searchResult = mutableListOf<Post>()
        if (filterModel == null || filterModel is PublicationPost) {
            val publicationPosts = publicationPostRepository.getAllByFields(filterModel) // Lista ya
            searchResult.addAll(publicationPosts)
        }
        if (filterModel == null || filterModel is PromotionPost) {
            val promotionPosts = promotionPostRepository.getAllByFields(filterModel) // Lista ya
            searchResult.addAll(promotionPosts)
        }
        return searchResult
    }


    suspend fun insert(category: Category): Int {
        return categoryRepository.insert(category).toInt()
    }

    suspend fun getCategoryById(id: Int): Category? {
        return categoryRepository.getByUniqueFields(Category(id = id)).firstOrNull()
    }

    suspend fun search(filterModel: Category? = null): MutableList<Category> {
        val searchResult = mutableListOf<Category>()
        categoryRepository.getAllByFields(filterModel).collect { searchResult.add(it) }
        return searchResult
    }

    suspend fun insert(report: Report): Int {
        return reportRepository.insert(report).toInt()
    }

    suspend fun getReportById(id: Int): Report? {
        return reportRepository.getByUniqueFields(Report(id = id)).firstOrNull()
    }

    suspend fun search(filterModel: Report? = null): MutableList<Report> {
        val searchResult = mutableListOf<Report>()
        reportRepository.getAllByFields(filterModel).collect { searchResult.add(it) }
        return searchResult
    }

    suspend fun insert(review: Review): Int {
        return reviewRepository.insert(review).toInt()
    }

    suspend fun getReviewById(id: Int): Review? {
        return reviewRepository.getByUniqueFields(Review(id = id)).firstOrNull()
    }

    suspend fun search(filterModel: Review? = null): MutableList<Review> {
        val searchResult = mutableListOf<Review>()
        reviewRepository.getAllByFields(filterModel).collect { searchResult.add(it) }
        return searchResult
    }

    suspend fun insert(variant: Variant): Int {
        return variantRepository.insert(variant).toInt()
    }

    suspend fun getVariantById(id: Int): Variant? {
        return variantRepository.getByUniqueFields(Variant(id = id)).firstOrNull()
    }

    suspend fun search(filterModel: Variant? = null): MutableList<Variant> {
        val searchResult = mutableListOf<Variant>()
        variantRepository.getAllByFields(filterModel).collect { searchResult.add(it) }
        return searchResult
    }

    suspend fun insert(variantMedia: VariantMedia): Int {
        return variantMediaRepository.insert(variantMedia).toInt()
    }

    suspend fun getVariantMediaById(id: Int): VariantMedia? {
        return variantMediaRepository.getByUniqueFields(VariantMedia(id = id)).firstOrNull()
    }

    suspend fun search(filterModel: VariantMedia? = null): MutableList<VariantMedia> {
        val searchResult = mutableListOf<VariantMedia>()
        variantMediaRepository.getAllByFields(filterModel).collect { searchResult.add(it) }
        return searchResult
    }

    suspend fun insert(variantColor: VariantColor): Int {
        return variantColorRepository.insert(variantColor).toInt()
    }

    suspend fun getVariantColorById(id: Int): VariantColor? {
        return variantColorRepository.getByUniqueFields(VariantColor(id = id)).firstOrNull()
    }

    suspend fun search(filterModel: VariantColor? = null): MutableList<VariantColor> {
        val searchResult = mutableListOf<VariantColor>()
        variantColorRepository.getAllByFields(filterModel).collect { searchResult.add(it) }
        return searchResult
    }

    suspend fun insert(variantSize: VariantSize): Int {
        return variantSizeRepository.insert(variantSize).toInt()
    }

    suspend fun getVariantSizeById(id: Int): VariantSize? {
        return variantSizeRepository.getByUniqueFields(VariantSize(id = id)).firstOrNull()
    }

    suspend fun search(filterModel: VariantSize? = null): MutableList<VariantSize> {
        val searchResult = mutableListOf<VariantSize>()
        variantSizeRepository.getAllByFields(filterModel).collect { searchResult.add(it) }
        return searchResult
    }

    suspend fun searchCategoriesByAccount(filterModel: Post? = null): MutableList<Category> {
        val searchResult = mutableListOf<Category>()
        postCategoryRepository.getCategoriesByPost(filterModel).collect { searchResult.add(it) }
        return searchResult
    }

    suspend fun searchPublicationPostsByCategory(filterModel: Category? = null): MutableList<PublicationPost> {
        val searchResult = mutableListOf<PublicationPost>()
        postCategoryRepository.getPublicationPostsByCategory(filterModel).collect { searchResult.add(it) }
        return searchResult
    }

    suspend fun searchPromotionPostsByCategory(filterModel: Category? = null): MutableList<PromotionPost> {
        val searchResult = mutableListOf<PromotionPost>()
        postCategoryRepository.getPromotionPostsByCategory(filterModel).collect { searchResult.add(it) }
        return searchResult
    }

    suspend fun searchCategoriesByPromotionPost(filterModel: PromotionPost? = null): MutableList<Category> {
        val searchResult = mutableListOf<Category>()
        promotionTargetRepository.getCategoriesByPromotionPost(filterModel).collect { searchResult.add(it) }
        return searchResult
    }

    suspend fun searchPublicationPostsByPromotionPost(filterModel: PromotionPost? = null): MutableList<PublicationPost> {
        val searchResult = mutableListOf<PublicationPost>()
        promotionTargetRepository.getPublicationPostsByPromotionPost(filterModel).collect { searchResult.add(it) }
        return searchResult
    }
}
