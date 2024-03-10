package com.example.pandorica.ui.passwordList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pandorica.domain.DeleteAccountUseCase
import com.example.pandorica.domain.GetPasswordsUseCase
import com.example.pandorica.network.DomainException
import com.example.pandorica.network.entity.vault.PasswordEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordListViewModel @Inject constructor(
    private val getPasswordsUseCase: GetPasswordsUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(PasswordListState())
    val state = _state.asStateFlow()

    fun onReload() = loadData()

    fun onDeleteClick(passwordEntry: PasswordEntry) = viewModelScope.launch {
        passwordEntry.name ?: return@launch
        try {
            deleteAccountUseCase.invoke(passwordEntry.name)
            loadData()
        } catch (e: Exception) {

        } finally {

        }
    }

    private fun loadData() = viewModelScope.launch {
        try {
            _state.update { it.copy(loading = true, error = false) }
            val passwords = getPasswordsUseCase.invoke().passwordEntries
            _state.update { it.copy(passwordEntries = passwords, loading = false) }
        } catch (e: DomainException) {
            _state.update { it.copy(error = true, loading = false) }
        }
    }
}