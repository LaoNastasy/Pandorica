package com.example.pandorica.ui.createaccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pandorica.domain.CreateAccountUseCase
import com.example.pandorica.network.entity.vault.PasswordEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val useCase: CreateAccountUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(CreateAccountState(isLoading = true))
    val state = _state.asStateFlow()

    fun onCreateClick() = viewModelScope.launch {
        try {
            _state.update { it.copy(isLoading = true) }
            useCase.invoke(PasswordEntry(state.value.login, state.value.password))
        } catch (e: Exception) {

        } finally {
            _state.update { it.copy(isLoading = false) }
        }
    }

    fun onLoginChanged(string: String) = _state.update { it.copy(login = string) }
    fun onPasswordChanged(string: String) = _state.update { it.copy(password = string) }
}
