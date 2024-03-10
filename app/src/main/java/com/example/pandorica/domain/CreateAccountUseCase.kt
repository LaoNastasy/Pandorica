package com.example.pandorica.domain

import com.example.pandorica.data.Repository
import com.example.pandorica.data.TokenRepository
import com.example.pandorica.network.entity.vault.PasswordEntry
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(entry: PasswordEntry) = repository.createAccount(entry)
}
