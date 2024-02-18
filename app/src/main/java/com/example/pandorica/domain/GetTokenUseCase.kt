package com.example.pandorica.domain

import com.example.pandorica.data.TokenRepository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(private val tokenRepository: TokenRepository) {
    fun invoke(): String? = tokenRepository.token
}
