package com.gonnadev.espacioscomunitariosapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gonnadev.espacioscomunitariosapp.data.core.TokenManager
import com.gonnadev.espacioscomunitariosapp.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val tokenManager: TokenManager
) :
    ViewModel() {

    private var _state = MutableStateFlow<LoginState>(LoginState.Loading)
    val state: StateFlow<LoginState> = _state

    fun login(user: String, password: String) {
        viewModelScope.launch {
            _state.value = LoginState.Loading
            val result = withContext(Dispatchers.IO) {
                loginUseCase(
                    user, password
                )
            }
            if (result != null) {
                _state.value = LoginState.Success(result.token, result.user)
                tokenManager.saveToken(result.token)
            } else {
                _state.value = LoginState.Error("Ha ocurrido un error")
            }
        }
    }
}