package com.example.pandorica.ui.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pandorica.domain.GetTokenUseCase
import com.example.pandorica.domain.SignInUseCase
import com.example.pandorica.domain.SignUpUseCase
import com.example.pandorica.network.DomainException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val getTokenUseCase: GetTokenUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(AuthorizationState())
    val state = _state.asStateFlow()

    init {
        checkIfSignedIn()
    }

    fun onLoginChanged(login: String) {
        _state.update { it.copy(login = login) }
    }

    fun onPasswordChanged(password: String) {
        _state.update { it.copy(password = password) }
    }

    fun onEnterApplicationHandled() {
        _state.update { it.copy(enterApplication = false) }
    }

    fun onSignInClick() = viewModelScope.launch {
        try {
            _state.update { it.copy(loading = true, error = null) }
            signInUseCase.invoke(state.value.login, state.value.password)
            _state.update { it.copy(enterApplication = true) }
        } catch (e: DomainException) {
            _state.update { it.copy(error = e) }
        } finally {
            _state.update { it.copy(loading = false) }
        }
    }


    fun onChangeAuthMethodClick() {
        _state.update {
            val newMethod =
                if (it.authMethod == AuthorizationMethod.signIn)
                    AuthorizationMethod.createAccount
                else AuthorizationMethod.signIn
            it.copy(authMethod = newMethod)
        }
    }

    fun onCreateAccountClick() = viewModelScope.launch {
        _state.update { it.copy(loading = true) }
        try {
            signUpUseCase.invoke(state.value.login, state.value.password, state.value.encodedSecretKey)
            _state.update {
                it.copy(enterApplication = true)
            }
        } catch (e: DomainException) {
            _state.update { it.copy(error = e) }
        } finally {
            _state.update { it.copy(loading = false) }
        }
    }

    private fun checkIfSignedIn() {
        _state.update { it.copy(loading = true) }
        val token = getTokenUseCase.invoke()
        if (token != null) _state.update { it.copy(enterApplication = true) }
        else _state.update { it.copy(loading = false) }
    }
}