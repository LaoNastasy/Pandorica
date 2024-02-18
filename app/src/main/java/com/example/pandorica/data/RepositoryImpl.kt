package com.example.pandorica.data

import com.example.pandorica.network.Api
import com.example.pandorica.network.RetrofitErrorHandler
import com.example.pandorica.network.entity.authorization.getAccessToken.GetAccessTokenRequest
import com.example.pandorica.network.entity.authorization.getAccessToken.GetAccessTokenResponse
import com.example.pandorica.network.entity.authorization.signIn.SignInRequest
import com.example.pandorica.network.entity.authorization.signIn.SignInResponse
import com.example.pandorica.network.entity.authorization.signUp.SignUpRequest
import com.example.pandorica.network.entity.authorization.signUp.SignUpResponse
import com.example.pandorica.network.entity.vault.GetVaultResponse
import com.example.pandorica.network.entity.vault.PasswordEntry
import com.example.pandorica.network.entity.vault.UpdateVaultRequest

class RepositoryImpl(
    private val retrofitErrorHandler: RetrofitErrorHandler,
    private val api: Api,
) : Repository {
    override suspend fun signUp(login: String, password: String): SignUpResponse =
        requireNotNull(retrofitErrorHandler.apiCall {
            api.signUp(
                SignUpRequest(login, password)
            )
        })


    override suspend fun signIn(login: String, password: String): SignInResponse =
        requireNotNull(retrofitErrorHandler.apiCall {
            api.signIn(
                SignInRequest(login, password)
            )
        })

    override suspend fun getAccessToken(refreshToken: String): GetAccessTokenResponse =
        requireNotNull(retrofitErrorHandler.apiCall {
            api.getAccessToken(
                GetAccessTokenRequest(refreshToken)
            )
        })

    override suspend fun getVault(token: String): GetVaultResponse =
        requireNotNull(retrofitErrorHandler.apiCall {
            api.getVault(token)
        })

    override suspend fun updateVault(
        token: String,
        addOrUpdate: Map<String, PasswordEntry>?,
        delete: List<String>?
    ) {
        retrofitErrorHandler.apiCall {
            api.updateVault(
                token,
                UpdateVaultRequest(
                    addOrUpdate, delete
                )
            )
        }
    }
}