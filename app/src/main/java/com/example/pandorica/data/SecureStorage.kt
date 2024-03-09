package com.example.pandorica.data

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.pandorica.di.Encrypted
import javax.inject.Inject

class SecureStorage @Inject constructor(
    @Encrypted private val sharedPreferences: SharedPreferences
) {

    fun putString(key: String, value: String?) = sharedPreferences.edit { putString(key, value) }

    fun getString(key: String) = sharedPreferences.getString(key, null)

    companion object {
        const val TOKEN_KEY = "TOKEN_KEY"
        const val REFRESH_TOKEN_KEY = "REFRESH_TOKEN_KEY"
    }
}
