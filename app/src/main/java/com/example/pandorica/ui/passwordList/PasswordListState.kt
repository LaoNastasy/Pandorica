package com.example.pandorica.ui.passwordList

import com.example.pandorica.network.entity.vault.PasswordEntry

data class PasswordListState(
    val passwordEntries: List<PasswordEntry> = emptyList(),
    val loading: Boolean = false,
    val error: Boolean = false,
)