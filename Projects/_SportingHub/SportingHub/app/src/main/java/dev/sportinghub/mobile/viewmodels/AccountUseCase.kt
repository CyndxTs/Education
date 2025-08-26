package dev.sportinghub.mobile.viewmodels

import dev.sportinghub.mobile.model.accounts.Account
import dev.sportinghub.mobile.model.accounts.AccountStatus
import dev.sportinghub.mobile.model.accounts.AccountType
import dev.sportinghub.mobile.model.accounts.Address
import dev.sportinghub.mobile.model.accounts.AdminAccount
import dev.sportinghub.mobile.model.accounts.BrandAccount
import dev.sportinghub.mobile.model.accounts.Notification
import dev.sportinghub.mobile.model.accounts.PersonalAccount
import dev.sportinghub.mobile.model.posts.Category
import dev.sportinghub.mobile.repository.accounts.AccountCategoryRepository
import dev.sportinghub.mobile.repository.accounts.AddressRepository
import dev.sportinghub.mobile.repository.accounts.AdminAccountRepository
import dev.sportinghub.mobile.repository.accounts.BrandAccountRepository
import dev.sportinghub.mobile.repository.accounts.NotificationRepository
import dev.sportinghub.mobile.repository.accounts.PersonalAccountRepository
import kotlinx.coroutines.flow.firstOrNull
import java.util.Date
import javax.inject.Inject

class AccountUseCase @Inject constructor(
    val accountCategoryRepository: AccountCategoryRepository,
    val addressRepository: AddressRepository,
    val adminAccountRepository: AdminAccountRepository,
    val brandAccountRepository: BrandAccountRepository,
    val notificationRepository: NotificationRepository,
    val personalAccountRepository: PersonalAccountRepository
) {
    suspend fun signUp(newAccount: Account): Account {
        when(newAccount) {
            is PersonalAccount -> personalAccountRepository.insert(newAccount)
            is BrandAccount -> brandAccountRepository.insert(newAccount)
            is AdminAccount -> adminAccountRepository.insert(newAccount)
        }
        return newAccount
    }

    suspend fun signIn(evalAccount: Account): Account? {
        when(evalAccount) {
            is PersonalAccount -> {
                val foundAccount = personalAccountRepository.getByUniqueFields(evalAccount).firstOrNull()
                if (foundAccount == null || foundAccount.password != evalAccount.password) return null
                foundAccount.status = AccountStatus.ONLINE
                personalAccountRepository.update(foundAccount)
                return foundAccount
            }
            is BrandAccount -> {
                val foundAccount = brandAccountRepository.getByUniqueFields(evalAccount).firstOrNull()
                if (foundAccount == null || foundAccount.password != evalAccount.password) return null
                foundAccount.status = AccountStatus.ONLINE
                brandAccountRepository.update(foundAccount)
                return foundAccount
            }
            is AdminAccount -> {
                val foundAccount = adminAccountRepository.getByUniqueFields(evalAccount).firstOrNull()
                if (foundAccount == null || foundAccount.password != evalAccount.password) return null
                foundAccount.status = AccountStatus.ONLINE
                adminAccountRepository.update(foundAccount)
                return foundAccount
            }
        }
        return null
    }

    suspend fun signOut(account: Account): Account? {
        account.status = AccountStatus.OFFLINE
        update(account)
        return null
    }

    suspend fun isFreeEmail(email: String): Boolean {
        val filterModel_PA = PersonalAccount(email = email)
        val filterModel_BA = BrandAccount(email = email)
        val filterModel_AA = AdminAccount(email = email)
        return personalAccountRepository.getByUniqueFields(filterModel_PA).firstOrNull() == null
               && brandAccountRepository.getByUniqueFields(filterModel_BA).firstOrNull() == null
               && adminAccountRepository.getByUniqueFields(filterModel_AA).firstOrNull() == null
    }

    suspend fun isFreeNickname(nickname: String): Boolean {
        val filterModel_PA = PersonalAccount(nickname = nickname)
        val filterModel_BA = BrandAccount(nickname = nickname)
        val filterModel_AA = AdminAccount(nickname = nickname)
        return personalAccountRepository.getByUniqueFields(filterModel_PA).firstOrNull() == null
               && brandAccountRepository.getByUniqueFields(filterModel_BA).firstOrNull() == null
               && adminAccountRepository.getByUniqueFields(filterModel_AA).firstOrNull() == null
    }

    suspend fun setGeneralFields(account: Account,photoUrl: String? = null, bio: String? = null): Int {
        account.bio = bio
        account.photoUrl = photoUrl
        update(account)
        return 0
    }

    suspend fun setSpecificFields(account: Account,firstName: String = "",lastName: String = "", birthDate: Date? = null,
                                  gender: String? = null, businessName: String = "",country: String = "",taxNumber: String = "",
                                  phoneNumber: String = ""): Int {
        when(account) {
            is PersonalAccount -> {
                account.firstName = firstName
                account.lastName = lastName
                account.birthDate = birthDate
                account.gender = gender
                personalAccountRepository.update(account)
            }
            is BrandAccount -> {
                account.businessName = businessName
                account.country = country
                account.taxNumber = taxNumber
                brandAccountRepository.update(account)
            }
            is AdminAccount -> {
                account.phoneNumber = phoneNumber
                adminAccountRepository.update(account)
            }
        }
        return 0
    }

    suspend fun insert(account: Account): Int {
        return try {
            when(account) {
                is AdminAccount -> adminAccountRepository.insert(account).toInt()
                is BrandAccount -> brandAccountRepository.insert(account).toInt()
                else -> personalAccountRepository.insert(account as PersonalAccount).toInt()
            }
        } catch (e: Exception) {
            -1
        }
    }

    suspend fun update(account: Account): Boolean {
        return try {
            when(account) {
                is PersonalAccount -> personalAccountRepository.update(account)
                is BrandAccount -> brandAccountRepository.update(account)
                is AdminAccount -> adminAccountRepository.update(account)
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun getAccountById(accountType: AccountType,id: Int): Account? {
        return when(accountType) {
            AccountType.PERSONAL -> personalAccountRepository.getByUniqueFields(PersonalAccount(id = id)).firstOrNull()
            AccountType.BRAND -> brandAccountRepository.getByUniqueFields(BrandAccount(id = id)).firstOrNull()
            AccountType.ADMIN -> adminAccountRepository.getByUniqueFields(AdminAccount(id = id)).firstOrNull()
        }
    }

    suspend fun getAllAccounts(types: List<AccountType>? = null): MutableList<Account> {
        val searchResult = mutableListOf<Account>()
        if(types == null || AccountType.PERSONAL in types) personalAccountRepository.getAllByFields().collect { searchResult.add(it) }
        if(types == null || AccountType.BRAND in types) brandAccountRepository.getAllByFields().collect { searchResult.add(it) }
        if(types == null || AccountType.ADMIN in types) adminAccountRepository.getAllByFields().collect { searchResult.add(it) }
        return searchResult
    }

    suspend fun search(filterModel: Account? = null, matchMode: Int = 0): MutableList<Account> {
        val searchResult = mutableListOf<Account>()
        if (filterModel == null || filterModel is PersonalAccount) {
            personalAccountRepository.getAllByFields(filterModel).collect { searchResult.add(it) }
        }
        if (filterModel == null || filterModel is BrandAccount) {
            brandAccountRepository.getAllByFields(filterModel).collect { searchResult.add(it) }
        }
        if (filterModel == null || filterModel is AdminAccount) {
            adminAccountRepository.getAllByFields(filterModel).collect { searchResult.add(it) }
        }
        return searchResult
    }

    suspend fun insert(address: Address): Int {
        return try {
            addressRepository.insert(address).toInt()
        } catch (e: Exception) {
            -1
        }
    }

    suspend fun getAddressById(id: Int): Address? {
        return addressRepository.getByUniqueFields(Address(id = id)).firstOrNull()
    }

    suspend fun search(filterModel: Address?): MutableList<Address> {
        val searchResult = mutableListOf<Address>()
        addressRepository.getAllByFields(filterModel).collect { searchResult.add(it) }
        return searchResult
    }

    suspend fun insert(notification: Notification): Int {
        return try {
            notificationRepository.insert(notification).toInt()
        } catch (e: Exception) {
            -1
        }
    }

    suspend fun getNotificationById(id: Int): Notification? {
        return notificationRepository.getByUniqueFields(Notification(id = id)).firstOrNull()
    }

    suspend fun search(filterModel: Notification?): MutableList<Notification> {
        val searchResult = mutableListOf<Notification>()
        notificationRepository.getAllByFields(filterModel).collect { searchResult.add(it) }
        return searchResult
    }

    suspend fun searchCategoriesByAccount(filterModel: Account?): MutableList<Category> {
        val searchResult = mutableListOf<Category>()
        accountCategoryRepository.getCategoriesByAccount(filterModel).collect { searchResult.add(it) }
        return searchResult
    }

    suspend fun searchBrandAccountsByCategory(filterModel: Category?): MutableList<BrandAccount> {
        val searchResult = mutableListOf<BrandAccount>()
        accountCategoryRepository.getBrandAccountsByCategory(filterModel).collect { searchResult.add(it) }
        return searchResult
    }

    suspend fun searchPersonalAccountsByCategory(filterModel: Category?): MutableList<PersonalAccount> {
        val searchResult = mutableListOf<PersonalAccount>()
        accountCategoryRepository.getPersonalAccountsByCategory(filterModel).collect { searchResult.add(it) }
        return searchResult
    }
}
