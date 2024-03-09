package com.example.pandorica.data

import com.example.pandorica.data.SecureStorage.Companion.REFRESH_TOKEN_KEY
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenInMemoryStorage: TokenInMemoryStorage,
    private val secureStorage: SecureStorage,
) : TokenRepository {
    override var token: String?
        get() = tokenInMemoryStorage.getToken().value
        set(value) = tokenInMemoryStorage.setToken(value)

    override var refreshToken: String?
        get() = secureStorage.getString(REFRESH_TOKEN_KEY)
        set(value) = secureStorage.putString(REFRESH_TOKEN_KEY, value)

    override fun updateTokens(accessToken: String, refreshToken: String) {
        this.token = accessToken
        this.refreshToken = refreshToken
    }
}