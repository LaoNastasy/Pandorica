package com.example.pandorica.data

interface TokenRepository {
    var token: String?

    fun updateTokens(accessToken: String, refreshToken: String)
}