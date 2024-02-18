package com.example.pandorica.ui.authorization

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pandorica.data.Repository
import com.example.pandorica.network.DomainException
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.pandorica.network.entity.vault.PasswordEntry
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
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

    fun apiTest() = viewModelScope.launch {
        try {

            val loginAndPassword = Pair("test9991", "test9991")
            val signUpResponse = repository.signUp(loginAndPassword.first, loginAndPassword.second)
            val signInResponse = repository.signIn(loginAndPassword.first, loginAndPassword.second)

            val refreshToken = signInResponse.refreshToken
            val getAccessTokenResponse = repository.getAccessToken(refreshToken)

            val accessToken = "Bearer " + getAccessTokenResponse.accessToken
            var vault = repository.getVault(accessToken)

            repository.updateVault(
                accessToken,
                mapOf(
                    "test3" to PasswordEntry("encodedLogin1", "encodedPassword1"),
                    "test4" to PasswordEntry("encodedLogin2", "encodedPassword2"),
                ),
                null
            )

            vault = repository.getVault(accessToken)
            println(vault)

            repository.updateVault(
                accessToken,
                null,
                listOf("test3")
            )

            vault = repository.getVault(accessToken)
            println(vault)

        } catch (e: DomainException) {
            println(1)
        } finally {
            println(2)
        }
    }
}