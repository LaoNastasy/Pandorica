package com.example.pandorica.ui.authorization

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pandorica.data.Repository
import com.example.pandorica.network.DomainException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthorizationViewModel(
    private val repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow(AuthorizationState())
    val state = _state.asStateFlow()

    var login by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    fun updateLogin(login: String) {
        this.login = login
    }

    fun updatePassword(password: String) {
        this.password = password
    }

    fun signIn() = viewModelScope.launch {
        try {
            _state.update { it.copy(loading = true, error = null) }
            val response = repository.signIn(login, password)
            _state.update { it.copy(authResponse = response) }
        } finally {
            _state.update { it.copy(loading = false) }
        }
    }

    fun changeAuthMethod() {
        _state.update {
            val newMethod =
                if (it.authMethod == AuthorizationMethod.signIn)
                    AuthorizationMethod.createAccount
                else AuthorizationMethod.signIn
            it.copy(authMethod = newMethod)
        }
    }

    fun createAccount() = viewModelScope.launch {
        try {
            val response = repository.signUp(login, password)
            _state.update {
                it.copy(
                    successPopup = true
                )
            }
            delay(300)
            _state.update {
                it.copy(
                    successPopup = false
                )
            }
        } catch (e: DomainException) {

        } finally {

        }
    }
}