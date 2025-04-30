package br.upf.deliveryapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.upf.deliveryapp.data.user.User
import br.upf.deliveryapp.data.user.UserDatabaseHelper
import br.upf.deliveryapp.data.user.UserRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserViewModel( private val repository : UserRepository, context: Context) : ViewModel() {

    private val _loginResult = MutableStateFlow<LoginResult>(LoginResult.Initial)
    val loginResult: StateFlow<LoginResult> = _loginResult
    private val dbHelper = UserDatabaseHelper(context)
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users.asStateFlow()

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
    fun getAllUsers() {
        val users = repository.getAllUsers()
        _users.value = users
    }
    fun insertUser(user: User) {
        repository.insertUser(user)
        getAllUsers()
    }
    fun updateUser(user: User) {
        repository.updateUser(user)
    }
    fun deleteUser(user: User) {
        repository.deleteUser(user.id)
        getAllUsers()
    }



}

  sealed class LoginResult {
    object Initial : LoginResult()
    object Success : LoginResult()
    data class Error(val message: String) : LoginResult()
}