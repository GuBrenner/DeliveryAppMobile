package br.upf.deliveryapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.upf.deliveryapp.data.user.UserDatabaseHelper
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserViewModel(context: Context) : ViewModel() {

    private val _loginResult = MutableStateFlow<LoginResult>(LoginResult.Initial)
    val loginResult: StateFlow<LoginResult> = _loginResult
    private val dbHelper = UserDatabaseHelper(context)

    fun authenticate(email: String, password: String) {
        viewModelScope.launch {
            val success = dbHelper.authenticate(email, password)
            if (success) {
                _loginResult.value = LoginResult.Success
            } else {
                _loginResult.value = LoginResult.Error("E-mail ou senha inválidas")
            }
        }
    }
}

  sealed class LoginResult {
    object Initial : LoginResult()
    object Success : LoginResult()
    data class Error(val message: String) : LoginResult()
}