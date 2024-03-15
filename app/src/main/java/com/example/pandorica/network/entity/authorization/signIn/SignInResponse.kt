package com.example.pandorica.network.entity.authorization.signIn

import com.example.pandorica.network.entity.authorization.AuthBaseResponse

class SignInResponse(
    accessToken: String,
    refreshToken: String,
    encodedSecretKey: String,
    salt: String
) : AuthBaseResponse(accessToken, refreshToken, encodedSecretKey, salt)