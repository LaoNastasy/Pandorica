package com.example.pandorica.data

import com.example.pandorica.network.entity.authorization.signIn.SignInResponse
import com.example.pandorica.network.entity.authorization.signUp.SignUpResponse

interface Repository {

    suspend fun signUp(login: String, password: String): SignUpResponse

    suspend fun signIn(login: String, password: String): SignInResponse
}