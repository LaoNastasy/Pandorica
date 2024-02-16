package com.example.pandorica.ui.passwordList

import com.example.pandorica.network.DomainException

data class PasswordListState (
    val login: String = "",
    val password: String = "",
    val successPopup: Boolean = false,
    val error: DomainException? = null
)