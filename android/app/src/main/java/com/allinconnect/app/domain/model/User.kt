package com.allinconnect.app.domain.model

enum class UserType {
    CLIENT,
    PRO
}

data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val username: String,
    val bio: String,
    val profileImageName: String,
    val publications: Int = 0,
    val subscribers: Int = 0,
    val subscriptions: Int = 0,
    val userType: UserType = UserType.CLIENT
) {
    val fullName: String
        get() = "$firstName $lastName"
}

