package com.example.pandorica.ui.createaccount

data class CreateAccountState(
    val isLoading: Boolean = true,
    val name: String = "",
    val login: String = "",
    val password: String = "",
    val goBack: Boolean = false,
)
