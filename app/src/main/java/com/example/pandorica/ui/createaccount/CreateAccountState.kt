package com.example.pandorica.ui.createaccount

data class CreateAccountState(
    val isLoading: Boolean = true,
    val login: String = "",
    val password: String = "",
)
