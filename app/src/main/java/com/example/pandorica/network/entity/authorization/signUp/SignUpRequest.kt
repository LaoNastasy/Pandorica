package com.example.pandorica.network.entity.authorization.signUp

import com.example.pandorica.network.entity.authorization.AuthBaseRequest

class SignUpRequest(
    login: String,
    password: String,
    encodedSecretKey: String,
    salt: String
): AuthBaseRequest(login, password)