package dev.sportinghub.mobile.repository.accounts

import androidx.sqlite.db.SimpleSQLiteQuery
import dev.sportinghub.mobile.client.features.SH_Formatter
import dev.sportinghub.mobile.model.accounts.Account
import dev.sportinghub.mobile.model.accounts.AccountCategory
import dev.sportinghub.mobile.model.accounts.AccountType
import dev.sportinghub.mobile.model.accounts.BrandAccount
import dev.sportinghub.mobile.model.accounts.PersonalAccount
import dev.sportinghub.mobile.model.posts.Category
import dev.sportinghub.mobile.room.accounts.AccountCategoryDatabaseDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AccountCategoryRepository @Inject constructor(private val accountCategoryDatabaseDAO: AccountCategoryDatabaseDAO){
    private var conditions = mutableListOf<String>()

    suspend fun insert(accountType: AccountType,accountId: Int,categoryId: Int): Long = accountCategoryDatabaseDAO.insert(AccountCategory(accountType,accountId, categoryId))


    suspend fun delete(accountType: AccountType,accountId: Int,categoryId: Int) = accountCategoryDatabaseDAO.delete(AccountCategory(accountType,accountId, categoryId))

    fun getPersonalAccountsByCategory(model: Category?): Flow<PersonalAccount> {
        var sql = "SELECT * FROM PERSONAL_ACCOUNT A"
        loadClauseConditions(0)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.JOIN)
        loadClauseConditions(model)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return accountCategoryDatabaseDAO.getPersonalAccountsByCategory(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    fun getBrandAccountsByCategory(model: Category?): Flow<BrandAccount> {
        var sql = "SELECT * FROM BRAND_ACCOUNT A"
        loadClauseConditions(0)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.JOIN)
        loadClauseConditions(model)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return accountCategoryDatabaseDAO.getBrandAccountsByCategory(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    fun getCategoriesByAccount(model: Account?): Flow<Category> {
        var sql = "SELECT * FROM CATEGORY C"
        when(model) {
            is PersonalAccount -> loadClauseConditions(1)
            else -> loadClauseConditions(2)
        }
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.JOIN)
        loadClauseConditions(model)
        sql = SH_Formatter.appendClause(sql,this.conditions,SH_Formatter.Clause.WHERE)
        return accountCategoryDatabaseDAO.getCategoriesByAccount(SimpleSQLiteQuery(sql)).flowOn(Dispatchers.IO).conflate()
    }

    private fun loadClauseConditions(matchMode: Int) {
        when(matchMode) {
            0 -> {
                this.conditions.add("ACCOUNT_CATEGORY AC ON A.id = AC.account_id")
                this.conditions.add("CATEGORY C ON C.id = AC.category_id")
            }
            1 -> {
                this.conditions.add("ACCOUNT_CATEGORY AC ON C.id = AC.category_id")
                this.conditions.add("PERSONAL_ACCOUNT A ON A.id = AC.account_id")
            }
            2 -> {
                this.conditions.add("ACCOUNT_CATEGORY AC ON C.id = AC.category_id")
                this.conditions.add("BRAND_ACCOUNT A ON A.id = AC.account_id")
            }
        }
    }

    private fun loadClauseConditions(model: Account?) {
        this.conditions = mutableListOf<String>()
        if(model == null) return
        model.id.takeIf { SH_Formatter.isValidCondition(it) }?.let {
            this.conditions.add("id = $it")
            return
        }
        model.email.takeIf { SH_Formatter.isValidCondition(it) }?.let {
            this.conditions.add("email LIKE ${SH_Formatter.toSqlString(it,1)}")
        }
        model.nickname.takeIf { SH_Formatter.isValidCondition(it) }?.let {
            this.conditions.add("nickname LIKE ${SH_Formatter.toSqlString(it,2)}")
        }
        model.status.takeIf { SH_Formatter.isValidCondition(it) }?.let {
            this.conditions.add("status = ${SH_Formatter.toSqlString(it)}")
        }
        when(model) {
            is PersonalAccount -> {
                model.firstName.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("first_name LIKE ${SH_Formatter.toSqlString(it,2)}")
                }
                model.lastName.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("last_name LIKE ${SH_Formatter.toSqlString(it,2)}")
                }
                model.birthDate.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("birthdate > ${SH_Formatter.toSqlString(it)}")
                }
                model.gender.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("gender LIKE ${SH_Formatter.toSqlString(it,2)}")
                }
            }
            is BrandAccount -> {
                model.businessName.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("business_name LIKE ${SH_Formatter.toSqlString(it,1)}")
                }
                model.country.takeIf { SH_Formatter.isValidCondition(it) }?.let {
                    this.conditions.add("country LIKE ${SH_Formatter.toSqlString(it,1)}")
                }
            }
        }
    }

    private fun loadClauseConditions(model: Category?) {
        this.conditions = mutableListOf<String>()
        if(model == null) return
        model.id.takeIf { SH_Formatter.isValidCondition(it) }?.let {
            this.conditions.add("id = $it")
            return
        }
        model.name.takeIf { SH_Formatter.isValidCondition(it) }?.let {
            this.conditions.add("name LIKE ${SH_Formatter.toSqlString(it,1)}")
        }
    }
}
