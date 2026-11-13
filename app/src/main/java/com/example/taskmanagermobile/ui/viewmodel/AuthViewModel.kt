package com.example.taskmanagermobile.ui.viewmodel

import android.app.Application
import android.database.sqlite.SQLiteConstraintException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanagermobile.auth.AuthManager
import com.example.taskmanagermobile.auth.UserSession
import com.example.taskmanagermobile.data.dal.UserDao
import com.example.taskmanagermobile.validation.RegisterValidator
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = UserDao(application)

    var loading by mutableStateOf(false)
        private set
    var done by mutableStateOf(false)
        private set
    var message by mutableStateOf<String?>(null)
        private set

    private fun execute(operation: suspend () -> Boolean) {
        if (loading || done) return
        loading = true
        message = null

        viewModelScope.launch {
            try {
                done = withContext(Dispatchers.IO) { operation() }
                if (!done) message = "E-mail ou senha inválidos."
            } catch (e: CancellationException) {
                throw e
            } catch (e: SQLiteConstraintException) {
                message = "Este e-mail já está cadastrado."
            } catch (e: IllegalArgumentException) {
                message = e.message
            } catch (e: Exception) {
                message = "Ocorreu um erro inesperado."
            } finally {
                loading = false
            }
        }
    }

    fun register(name: String, email: String, password: String) = execute {
        val cleanName = name.trim()
        val cleanEmail = email.trim().lowercase()

        RegisterValidator.validate(cleanName, cleanEmail, password)

        val salt = AuthManager.createSalt()
        val hash = AuthManager.createHash(password, salt)
        try {
            dao.register(cleanName, cleanEmail, hash, salt)
            true
        } finally {
            hash.fill(0)
        }
    }

    fun login(email: String, password: String) = execute {
        require(email.length <= 254 && password.length in 6..128) {
            "E-mail ou senha inválidos."
        }

        val saved = dao.findPassword(email.trim().lowercase())
        val hash = saved?.hash ?: ByteArray(32)
        val salt = saved?.salt ?: ByteArray(16)

        if (saved != null && AuthManager.validatePassword(password, salt, hash)) {
            UserSession.start(saved.userId)
            true
        } else false
    }
}
