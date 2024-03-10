package com.example.pandorica.network


import com.example.pandorica.network.entity.authorization.getAccessToken.GetAccessTokenRequest
import com.example.pandorica.network.entity.authorization.getAccessToken.GetAccessTokenResponse
import com.example.pandorica.network.entity.authorization.signIn.SignInRequest
import com.example.pandorica.network.entity.authorization.signIn.SignInResponse
import com.example.pandorica.network.entity.authorization.signUp.SignUpRequest
import com.example.pandorica.network.entity.authorization.signUp.SignUpResponse
import com.example.pandorica.network.entity.vault.GetVaultResponse
import com.example.pandorica.network.entity.vault.UpdateVaultRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface Api {
    @POST("auth/signUp")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>

    @POST("auth/signIn")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>

    @POST("auth/getAccessToken")
    suspend fun getAccessToken(@Body request: GetAccessTokenRequest): Response<GetAccessTokenResponse>

    @GET("vault")
    suspend fun getVault(): Response<GetVaultResponse>

    @POST("vault/update")
    suspend fun updateVault(@Body request: UpdateVaultRequest): Response<Unit>

}
