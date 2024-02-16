package com.example.pandorica.network


import com.example.pandorica.network.entity.authorization.signIn.SignInRequest
import com.example.pandorica.network.entity.authorization.signIn.SignInResponse
import com.example.pandorica.network.entity.authorization.signUp.SignUpRequest
import com.example.pandorica.network.entity.authorization.signUp.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {
    @POST("auth/signUp")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>

    @POST("auth/signIn")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>
}
