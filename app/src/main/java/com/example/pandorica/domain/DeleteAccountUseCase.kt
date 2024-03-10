package com.example.pandorica.domain

import com.example.pandorica.data.Repository
import javax.inject.Inject

class DeleteAccountUseCase @Inject constructor(private val repository: Repository) {
    suspend fun invoke(name: String) = repository.deleteAccount(name)
}
