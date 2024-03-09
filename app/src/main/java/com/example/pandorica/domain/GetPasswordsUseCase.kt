package com.example.pandorica.domain

import com.example.pandorica.data.Repository
import javax.inject.Inject

class GetPasswordsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun invoke() = repository.getVault()
}