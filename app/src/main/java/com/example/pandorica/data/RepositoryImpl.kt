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
    override suspend fun signUp(login: String, password: String, encodedSecretKey: String, salt: String): SignUpResponse = requireNotNull(
        retrofitErrorHandler.apiCall {
            api.signUp(
                SignUpRequest(login, password, encodedSecretKey, salt)
            )
        }
    )


    override suspend fun signIn(login: String, password: String): SignInResponse = requireNotNull(
        retrofitErrorHandler.apiCall {
            api.signIn(
                SignInRequest(login, password)
            )
        }
    )

    override suspend fun getAccessToken(refreshToken: String): GetAccessTokenResponse =
        requireNotNull(
            retrofitErrorHandler.apiCall {
                api.getAccessToken(
                    GetAccessTokenRequest(refreshToken)
                )
            }
        )

    override suspend fun getVault(): GetVaultResponse = requireNotNull(
        retrofitErrorHandler.apiCall {
            api.getVault()
        }
    )

    override suspend fun createAccount(passwordEntry: PasswordEntry) {
        retrofitErrorHandler.apiCall {
            api.updateVault(UpdateVaultRequest(addOrUpdate = listOf(passwordEntry)))
        }
    }

    override suspend fun deleteAccount(name: String) {
        retrofitErrorHandler.apiCall {
            api.updateVault(UpdateVaultRequest(delete = listOf(name)))
        }
    }
}