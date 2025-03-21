package com.gonnadev.espacioscomunitariosapp.ui.login

sealed class LoginState {
    data object Loading : LoginState()
    data class Error(val error: String) : LoginState()
    data class Success(val token: String, val user: Int) : LoginState()
}