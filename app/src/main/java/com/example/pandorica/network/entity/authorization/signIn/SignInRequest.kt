package com.example.pandorica.network.entity.authorization.signIn

data class SignInRequest(
    val login: String,
    val password: String
)