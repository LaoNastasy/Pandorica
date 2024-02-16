package com.example.pandorica.network.entity.authorization.signUp

import com.example.pandorica.network.entity.authorization.AuthBaseResponse

class SignUpResponse(
    accessToken: String,
    refreshToken: String,
): AuthBaseResponse(accessToken, refreshToken)