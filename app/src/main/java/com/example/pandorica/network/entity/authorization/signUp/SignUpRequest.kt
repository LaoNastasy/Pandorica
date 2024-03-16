package com.example.pandorica.network.entity.authorization.signUp

data class SignUpRequest(
    val login: String,
    val password: String,
    val encodedSecretKey: String,
    val salt: String
)