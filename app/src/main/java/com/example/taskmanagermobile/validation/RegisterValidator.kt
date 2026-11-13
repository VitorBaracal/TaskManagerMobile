package com.example.taskmanagermobile.validation

import android.util.Patterns

object RegisterValidator {
    fun validate(name: String, email: String, password: String) {
        require(email.length <= 254 && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            "Informe um e-mail válido."
        }
        validateName(name)
        validatePassword(password)
    }

    fun validate(name: String, password: String) {
        validateName(name)
        if (password.isNotEmpty()) validatePassword(password)
    }

    private fun validateName(name: String) {
        require(name.length in 3..100) {
            "Informe um nome entre 3 e 100 caracteres."
        }
    }

    private fun validatePassword(password: String) {
        require(password.length in 6..128 && password.isNotBlank()) {
            "Use uma senha entre 6 e 128 caracteres."
        }
    }
}
