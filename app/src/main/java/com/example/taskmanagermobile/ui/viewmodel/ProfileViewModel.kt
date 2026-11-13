package com.example.taskmanagermobile.ui.viewmodel

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanagermobile.auth.UserSession
import com.example.taskmanagermobile.data.dal.UserDao
import com.example.taskmanagermobile.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = UserDao(application)

    var user by mutableStateOf<User?>(null)
        private set

    fun load() {
        val id = UserSession.userId ?: return
        viewModelScope.launch {
            user = withContext(Dispatchers.IO) { dao.findById(id) }
        }
    }

    fun savePhoto(uri: Uri) {
        val id = UserSession.userId ?: return

        try {
            getApplication<Application>().contentResolver.takePersistableUriPermission(
                uri, Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        } catch (_: SecurityException) {
        }

        viewModelScope.launch {
            withContext(Dispatchers.IO) { dao.updatePhoto(id, uri.toString()) }
            user = user?.copy(photo = uri.toString())
        }
    }
}
