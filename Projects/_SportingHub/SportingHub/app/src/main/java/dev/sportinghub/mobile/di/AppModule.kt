package dev.sportinghub.mobile.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.sportinghub.mobile.room.SportingHubDatabase
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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): SportingHubDatabase {
        return Room.databaseBuilder(context, SportingHubDatabase::class.java, "sportinghub_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesAccountCategoryDAO(db: SportingHubDatabase): AccountCategoryDatabaseDAO = db.accountCategoryDAO()

    @Singleton
    @Provides
    fun providesAddressDAO(db: SportingHubDatabase): AddressDatabaseDAO = db.addressDAO()

    @Singleton
    @Provides
    fun providesAdminAccountDAO(db: SportingHubDatabase): AdminAccountDatabaseDAO = db.adminAccountDAO()

    @Singleton
    @Provides
    fun providesBrandAccountDAO(db: SportingHubDatabase): BrandAccountDatabaseDAO = db.brandAccountDAO()

    @Singleton
    @Provides
    fun providesNotificationDAO(db: SportingHubDatabase): NotificationDatabaseDAO = db.notificationDAO()

    @Singleton
    @Provides
    fun providesPersonalAccountDAO(db: SportingHubDatabase): PersonalAccountDatabaseDAO = db.personalAccountDAO()

    @Singleton
    @Provides
    fun providesOrderDatabaseDAO(db: SportingHubDatabase): OrderDatabaseDAO = db.orderDatabaseDAO()

    @Singleton
    @Provides
    fun providesOrderRowDatabaseDAO(db: SportingHubDatabase): OrderRowDatabaseDAO = db.orderRowDatabaseDAO()

    @Singleton
    @Provides
    fun providesCategoryDatabaseDAO(db: SportingHubDatabase): CategoryDatabaseDAO = db.categoryDatabaseDAO()

    @Singleton
    @Provides
    fun providesPostCategoryDatabaseDAO(db: SportingHubDatabase): PostCategoryDatabaseDAO = db.postCategoryDatabaseDAO()

    @Singleton
    @Provides
    fun providesPromotionPostDatabaseDAO(db: SportingHubDatabase): PromotionPostDatabaseDAO = db.promotionPostDatabaseDAO()

    @Singleton
    @Provides
    fun providesPromotionTargetDatabaseDAO(db: SportingHubDatabase): PromotionTargetDatabaseDAO = db.promotionTargetDatabaseDAO()

    @Singleton
    @Provides
    fun providesPublicationPostDatabaseDAO(db: SportingHubDatabase): PublicationPostDatabaseDAO = db.publicationPostDatabaseDAO()

    @Singleton
    @Provides
    fun providesReportDatabaseDAO(db: SportingHubDatabase): ReportDatabaseDAO = db.reportDatabaseDAO()

    @Singleton
    @Provides
    fun providesReviewDatabaseDAO(db: SportingHubDatabase): ReviewDatabaseDAO = db.reviewDatabaseDAO()

    @Singleton
    @Provides
    fun providesVariantColorDatabaseDAO(db: SportingHubDatabase): VariantColorDatabaseDAO = db.variantColorDatabaseDAO()

    @Singleton
    @Provides
    fun providesVariantDatabaseDAO(db: SportingHubDatabase): VariantDatabaseDAO = db.variantDatabaseDAO()

    @Singleton
    @Provides
    fun providesVariantMediaDatabaseDAO(db: SportingHubDatabase): VariantMediaDatabaseDAO = db.variantMediaDatabaseDAO()

    @Singleton
    @Provides
    fun providesVariantSizeDatabaseDAO(db: SportingHubDatabase): VariantSizeDatabaseDAO = db.variantSizeDatabaseDAO()
}