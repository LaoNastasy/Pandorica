package com.example.pandorica.ui.authorization

import com.example.pandorica.network.DomainException

data class AuthorizationState(
    val login: String = "",
    val password: String = "",
    val loading: Boolean = false,
    val error: DomainException? = null,
    val authMethod: AuthorizationMethod = AuthorizationMethod.signIn,
    val enterApplication: Boolean = false,
)

enum class AuthorizationMethod {
    createAccount, signIn
}