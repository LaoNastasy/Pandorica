package com.example.pandorica.ui.authorization

import com.example.pandorica.network.DomainException
import com.example.pandorica.network.entity.authorization.AuthBaseResponse

data class AuthorizationState(
    val login: String = "",
    val password: String = "",
    val loading: Boolean = false,
    val successPopup: Boolean = false,
    val error: DomainException? = null,
    val authMethod: AuthorizationMethod = AuthorizationMethod.signIn,
    val enterApplication: Boolean = false,
)

enum class AuthorizationMethod {
    createAccount, signIn
}