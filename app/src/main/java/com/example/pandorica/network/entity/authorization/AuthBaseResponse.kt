package com.example.pandorica.network.entity.authorization

open class AuthBaseResponse(
    open val accessToken: String,
    open val refreshToken: String,
    open val encodedSecretKey: String,
    open val salt: String
)