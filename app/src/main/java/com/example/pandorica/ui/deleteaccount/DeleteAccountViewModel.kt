package com.example.pandorica.ui.deleteaccount

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pandorica.domain.DeleteAccountUseCase
import com.example.pandorica.navigation.DeleteAccountDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeleteAccountViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val deleteAccountUseCase: DeleteAccountUseCase,
) : ViewModel() {
    private val name: String = DeleteAccountDestination.extractName(savedStateHandle)

    private val _state = MutableStateFlow(DeleteAccountState(name))
    val state = _state.asStateFlow()

    fun onConfirmClick() = viewModelScope.launch {
        try {
            deleteAccountUseCase.invoke(name)
            _state.update { it.copy(goBack = true) }
        } catch (e: Exception) {

        } finally {

        }
    }

    fun onGoBackHandled() = _state.update { it.copy(goBack = false) }
}