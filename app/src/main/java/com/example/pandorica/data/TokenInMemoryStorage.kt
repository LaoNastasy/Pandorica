package com.example.pandorica.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class TokenInMemoryStorage @Inject constructor(private val secureStorage: SecureStorage){
    private var _token: MutableStateFlow<String?> =
        MutableStateFlow(secureStorage.getString(SecureStorage.TOKEN_KEY))

    fun getToken(): StateFlow<String?> = _token.asStateFlow()
    fun setToken(value: String?) {
        _token.update { value }
        secureStorage.putString(SecureStorage.TOKEN_KEY, value)
    }
}
