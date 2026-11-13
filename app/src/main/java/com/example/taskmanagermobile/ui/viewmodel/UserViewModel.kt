package com.example.taskmanagermobile.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanagermobile.auth.AuthManager
import com.example.taskmanagermobile.auth.UserSession
import com.example.taskmanagermobile.data.dal.UserDao
import com.example.taskmanagermobile.data.model.User
import com.example.taskmanagermobile.validation.RegisterValidator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = UserDao(application)

    val currentUserId: Long? get() = UserSession.userId

    var users by mutableStateOf<List<User>>(emptyList())
        private set
    var name by mutableStateOf("")
    var email by mutableStateOf("")
        private set
    var newPassword by mutableStateOf("")
    var saved by mutableStateOf(false)
        private set
    var message by mutableStateOf<String?>(null)
        private set

    fun list() {
        viewModelScope.launch {
            users = withContext(Dispatchers.IO) { dao.list() }
        }
    }

    fun load(id: Long) {
        viewModelScope.launch {
            val user = withContext(Dispatchers.IO) { dao.findById(id) } ?: return@launch
            name = user.name
            email = user.email
        }
    }

    fun save(id: Long) {
        try {
            RegisterValidator.validate(name.trim(), newPassword)
        } catch (e: IllegalArgumentException) {
            message = e.message
            return
        }

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (newPassword.isEmpty()) {
                    dao.update(id, name.trim())
                } else {
                    val salt = AuthManager.createSalt()
                    val hash = AuthManager.createHash(newPassword, salt)
                    try {
                        dao.update(id, name.trim(), hash, salt)
                    } finally {
                        hash.fill(0)
                    }
                }
            }
            saved = true
        }
    }

    fun delete(id: Long) {
        if (id == currentUserId) {
            message = "Você não pode excluir o próprio usuário."
            return
        }

        viewModelScope.launch {
            withContext(Dispatchers.IO) { dao.delete(id) }
            users = users.filterNot { it.id == id }
        }
    }
}
