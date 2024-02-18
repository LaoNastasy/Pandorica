package com.example.pandorica.data

import com.example.pandorica.network.entity.authorization.getAccessToken.GetAccessTokenResponse
import com.example.pandorica.network.entity.authorization.signIn.SignInResponse
import com.example.pandorica.network.entity.authorization.signUp.SignUpResponse
import com.example.pandorica.network.entity.vault.GetVaultResponse
import com.example.pandorica.network.entity.vault.PasswordEntry

interface Repository {

    suspend fun signUp(login: String, password: String): SignUpResponse

    suspend fun signIn(login: String, password: String): SignInResponse

    suspend fun getAccessToken(refreshToken: String): GetAccessTokenResponse

    suspend fun getVault(token: String): GetVaultResponse

    suspend fun updateVault(token: String, addOrUpdate: Map<String, PasswordEntry>?, delete: List<String>?)
}