package com.example.pandorica.network.entity.vault

class UpdateVaultRequest(
    val addOrUpdate: Map<String, PasswordEntry>?,
    val delete: List<String>?
)