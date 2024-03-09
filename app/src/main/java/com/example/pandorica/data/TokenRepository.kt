package com.example.pandorica.data

interface TokenRepository {
    var token: String?
    var refreshToken: String?

    fun updateTokens(accessToken: String, refreshToken: String)
}