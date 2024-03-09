package com.example.pandorica.data

import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenInMemoryStorage: TokenInMemoryStorage,
) : TokenRepository {
    override var token: String?
        get() = tokenInMemoryStorage.getToken().value
        set(value) = tokenInMemoryStorage.setToken(value)

    override fun updateTokens(accessToken: String, refreshToken: String) {
        this.token = accessToken
    }
}