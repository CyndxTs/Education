package dev.sportinghub.mobile.model.accounts

interface Account {
    val id: Int?
    var email: String
    var nickname: String
    var password: String
    var status: AccountStatus?
    var bio: String?
    var photoUrl: String?
}
