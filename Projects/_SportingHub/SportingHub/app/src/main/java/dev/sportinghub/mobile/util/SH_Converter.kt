package dev.sportinghub.mobile.util

import androidx.room.TypeConverter
import dev.sportinghub.mobile.model.accounts.AccountStatus
import dev.sportinghub.mobile.model.accounts.AccountType
import dev.sportinghub.mobile.model.accounts.NotificationType
import dev.sportinghub.mobile.model.orders.OrderRowStatus
import dev.sportinghub.mobile.model.orders.OrderStatus
import dev.sportinghub.mobile.model.posts.PostStatus
import dev.sportinghub.mobile.model.posts.PostType
import dev.sportinghub.mobile.model.posts.TargetType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

object SH_Converter {
    @TypeConverter
    @JvmStatic
    fun fromDate(date: Date?): Long? = date?.time

    @TypeConverter
    @JvmStatic
    fun toDate(timestamp: Long?): Date? = timestamp?.let { Date(it) }

    @TypeConverter
    @JvmStatic
    fun fromLocalDateTime(dateTime: LocalDateTime?): String? = dateTime?.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)

    @TypeConverter
    @JvmStatic
    fun toLocalDateTime(dateTimeString: String?): LocalDateTime? = dateTimeString?.let { LocalDateTime.parse(it, DateTimeFormatter.ISO_LOCAL_DATE_TIME) }

    @TypeConverter
    @JvmStatic
    fun fromAccountStatus(state: AccountStatus?): String? = state?.name

    @TypeConverter
    @JvmStatic
    fun toAccountStatus(value: String?): AccountStatus? = value?.let { it -> enumValueOf<AccountStatus>(it) }

    @TypeConverter
    @JvmStatic
    fun fromAccountType(state: AccountType?): String? = state?.name

    @TypeConverter
    @JvmStatic
    fun toAccountType(value: String?): AccountType? = value?.let { it -> enumValueOf<AccountType>(it) }

    @TypeConverter
    @JvmStatic
    fun fromNotificationType(state: NotificationType?): String? = state?.name

    @TypeConverter
    @JvmStatic
    fun toNotificationType(value: String?): NotificationType? = value?.let { it -> enumValueOf<NotificationType>(it) }

    @TypeConverter
    @JvmStatic
    fun fromOrderRowStatus(state: OrderRowStatus?): String? = state?.name

    @TypeConverter
    @JvmStatic
    fun toOrderRowStatus(value: String?): OrderRowStatus? = value?.let { it -> enumValueOf<OrderRowStatus>(it) }

    @TypeConverter
    @JvmStatic
    fun fromOrderStatus(state: OrderStatus?): String? = state?.name

    @TypeConverter
    @JvmStatic
    fun toOrderStatus(value: String?): OrderStatus? = value?.let { it -> enumValueOf<OrderStatus>(it) }

    @TypeConverter
    @JvmStatic
    fun fromPostStatus(state: PostStatus?): String? = state?.name

    @TypeConverter
    @JvmStatic
    fun toPostStatus(value: String?): PostStatus? = value?.let { it -> enumValueOf<PostStatus>(it) }

    @TypeConverter
    @JvmStatic
    fun fromPostType(state: PostType?): String? = state?.name

    @TypeConverter
    @JvmStatic
    fun toPostType(value: String?): PostType? = value?.let { it -> enumValueOf<PostType>(it) }

    @TypeConverter
    @JvmStatic
    fun fromTargetType(state: TargetType?): String? = state?.name

    @TypeConverter
    @JvmStatic
    fun toTargetType(value: String?): TargetType? = value?.let { it -> enumValueOf<TargetType>(it) }
}