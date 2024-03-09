package com.example.pandorica.domain

import com.example.pandorica.data.Repository
import com.example.pandorica.data.TokenRepository
import javax.inject.Inject

class RefreshTokenUseCase @Inject constructor(
    private val repository: Repository,
    private val tokenRepository: TokenRepository
) {
    suspend fun invoke() = repository.getAccessToken(tokenRepository.refreshToken!!)
        .also {
            tokenRepository.updateTokens(it.accessToken, it.refreshToken)
        }
}
