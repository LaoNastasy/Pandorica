package com.example.pandorica.domain

import com.example.pandorica.data.Repository
import com.example.pandorica.data.TokenRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepo: Repository,
    private val tokenRepository: TokenRepository,
) {
    suspend fun invoke(login: String, password: String) {
        val response = authRepo.signIn(login, password)
        tokenRepository.updateTokens(response.accessToken, response.refreshToken)
    }
}
