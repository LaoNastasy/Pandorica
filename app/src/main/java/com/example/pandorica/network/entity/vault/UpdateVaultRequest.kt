package com.example.pandorica.network.entity.vault

class UpdateVaultRequest(
    val addOrUpdate: List<PasswordEntry>? = null,
    val delete: List<String>? = null
)