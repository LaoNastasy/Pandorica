package com.example.pandorica.network.entity.authorization.signUp

import com.example.pandorica.network.entity.authorization.AuthBaseResponse

class SignUpResponse(
    accessToken: String,
    refreshToken: String,
    encodedSecretKey: String,
    salt: String
): AuthBaseResponse(accessToken, refreshToken, encodedSecretKey, salt)