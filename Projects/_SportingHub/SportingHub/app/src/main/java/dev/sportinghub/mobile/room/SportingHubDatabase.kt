package dev.sportinghub.mobile.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.sportinghub.mobile.model.accounts.AccountCategory
import dev.sportinghub.mobile.model.accounts.Address
import dev.sportinghub.mobile.model.accounts.AdminAccount
import dev.sportinghub.mobile.model.accounts.BrandAccount
import dev.sportinghub.mobile.model.accounts.Notification
import dev.sportinghub.mobile.model.accounts.PersonalAccount
import dev.sportinghub.mobile.model.orders.Order
import dev.sportinghub.mobile.model.orders.OrderRow
import dev.sportinghub.mobile.model.posts.Category
import dev.sportinghub.mobile.model.posts.PostCategory
import dev.sportinghub.mobile.model.posts.PromotionPost
import dev.sportinghub.mobile.model.posts.PromotionTarget
import dev.sportinghub.mobile.model.posts.PublicationPost
import dev.sportinghub.mobile.model.posts.Report
import dev.sportinghub.mobile.model.posts.Review
import dev.sportinghub.mobile.model.posts.Variant
import dev.sportinghub.mobile.model.posts.VariantColor
import dev.sportinghub.mobile.model.posts.VariantMedia
import dev.sportinghub.mobile.model.posts.VariantSize
import dev.sportinghub.mobile.room.accounts.AccountCategoryDatabaseDAO
import dev.sportinghub.mobile.room.accounts.AddressDatabaseDAO
import dev.sportinghub.mobile.room.accounts.AdminAccountDatabaseDAO
import dev.sportinghub.mobile.room.accounts.BrandAccountDatabaseDAO
import dev.sportinghub.mobile.room.accounts.NotificationDatabaseDAO
import dev.sportinghub.mobile.room.accounts.PersonalAccountDatabaseDAO
import dev.sportinghub.mobile.room.orders.OrderDatabaseDAO
import dev.sportinghub.mobile.room.orders.OrderRowDatabaseDAO
import dev.sportinghub.mobile.room.posts.CategoryDatabaseDAO
import dev.sportinghub.mobile.room.posts.PostCategoryDatabaseDAO
import dev.sportinghub.mobile.room.posts.PromotionPostDatabaseDAO
import dev.sportinghub.mobile.room.posts.PromotionTargetDatabaseDAO
import dev.sportinghub.mobile.room.posts.PublicationPostDatabaseDAO
import dev.sportinghub.mobile.room.posts.ReportDatabaseDAO
import dev.sportinghub.mobile.room.posts.ReviewDatabaseDAO
import dev.sportinghub.mobile.room.posts.VariantColorDatabaseDAO
import dev.sportinghub.mobile.room.posts.VariantDatabaseDAO
import dev.sportinghub.mobile.room.posts.VariantMediaDatabaseDAO
import dev.sportinghub.mobile.room.posts.VariantSizeDatabaseDAO
import dev.sportinghub.mobile.util.SH_Converter

@Database(
    entities = [
        // ACCOUNTS
        AccountCategory::class,
        Address::class,
        AdminAccount::class,
        BrandAccount::class,
        Notification::class,
        PersonalAccount::class,
        // ORDERS
        Order::class,
        OrderRow::class,
        // POSTS
        Category::class,
        PostCategory::class,
        PromotionPost::class,
        PromotionTarget::class,
        PublicationPost::class,
        Report::class,
        Review::class,
        Variant::class,
        VariantColor::class,
        VariantMedia::class,
        VariantSize::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(SH_Converter::class)
abstract class SportingHubDatabase: RoomDatabase() {
    // ACCOUNTS
    abstract fun accountCategoryDAO(): AccountCategoryDatabaseDAO
    abstract fun addressDAO(): AddressDatabaseDAO
    abstract fun adminAccountDAO(): AdminAccountDatabaseDAO
    abstract fun brandAccountDAO(): BrandAccountDatabaseDAO
    abstract fun notificationDAO(): NotificationDatabaseDAO
    abstract fun personalAccountDAO(): PersonalAccountDatabaseDAO
    // ORDERS
    abstract fun orderDatabaseDAO(): OrderDatabaseDAO
    abstract fun orderRowDatabaseDAO(): OrderRowDatabaseDAO
    // POSTS
    abstract fun categoryDatabaseDAO(): CategoryDatabaseDAO
    abstract fun postCategoryDatabaseDAO(): PostCategoryDatabaseDAO
    abstract fun promotionPostDatabaseDAO(): PromotionPostDatabaseDAO
    abstract fun promotionTargetDatabaseDAO(): PromotionTargetDatabaseDAO
    abstract fun publicationPostDatabaseDAO(): PublicationPostDatabaseDAO
    abstract fun reportDatabaseDAO(): ReportDatabaseDAO
    abstract fun reviewDatabaseDAO(): ReviewDatabaseDAO
    abstract fun variantColorDatabaseDAO(): VariantColorDatabaseDAO
    abstract fun variantDatabaseDAO(): VariantDatabaseDAO
    abstract fun variantMediaDatabaseDAO(): VariantMediaDatabaseDAO
    abstract fun variantSizeDatabaseDAO(): VariantSizeDatabaseDAO
}