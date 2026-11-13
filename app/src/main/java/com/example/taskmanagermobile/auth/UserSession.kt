package com.example.taskmanagermobile.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object UserSession {
    var userId by mutableStateOf<Long?>(null)
        private set

    fun start(id: Long) {
        require(id > 0)
        userId = id
    }

    fun end() {
        userId = null
    }
}
