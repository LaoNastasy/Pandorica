package com.example.pandorica.data

import com.example.pandorica.network.Api
import com.example.pandorica.network.RetrofitErrorHandler
import com.example.pandorica.network.entity.authorization.signIn.SignInRequest
import com.example.pandorica.network.entity.authorization.signIn.SignInResponse
import com.example.pandorica.network.entity.authorization.signUp.SignUpRequest
import com.example.pandorica.network.entity.authorization.signUp.SignUpResponse

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
}