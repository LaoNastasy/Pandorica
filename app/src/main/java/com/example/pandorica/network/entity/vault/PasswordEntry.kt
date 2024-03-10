package com.example.pandorica.network.entity.vault

class PasswordEntry(
    val name: String?,
    val encodedLogin: String?,
    val encodedPassword: String,
    val timestamp: Long = System.currentTimeMillis()
)