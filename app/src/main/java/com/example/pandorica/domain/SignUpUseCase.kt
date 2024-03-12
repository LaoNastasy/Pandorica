package com.example.pandorica.domain

import com.example.pandorica.data.Repository
import com.example.pandorica.data.TokenRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepo: Repository,
    private val tokenRepository: TokenRepository,
) {
    suspend fun invoke(login: String, password: String, encodedSecretKey: String) {
        val response = authRepo.signUp(login, password, encodedSecretKey)
        tokenRepository.updateTokens(response.accessToken, response.refreshToken)
    }
}
