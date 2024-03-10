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
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
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

    override suspend fun getVault(): GetVaultResponse =
        requireNotNull(retrofitErrorHandler.apiCall {
            api.getVault()
        })

    override suspend fun updateVault(
        addOrUpdate: Map<String, PasswordEntry>?,
        delete: List<String>?
    ) {
        retrofitErrorHandler.apiCall {
            api.updateVault(
                UpdateVaultRequest(
                    addOrUpdate, delete
                )
            )
        }
    }
}