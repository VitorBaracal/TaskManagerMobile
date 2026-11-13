package com.example.taskmanagermobile.data.model

data class User(
    val id: Long,
    val name: String,
    val email: String,
    val photo: String? = null
)
