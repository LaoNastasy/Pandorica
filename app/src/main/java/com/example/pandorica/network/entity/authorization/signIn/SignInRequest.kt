package com.example.pandorica.network.entity.authorization.signIn

import com.example.pandorica.network.entity.authorization.AuthBaseRequest

class SignInRequest(
    login: String,
    password: String
) : AuthBaseRequest(login, password)