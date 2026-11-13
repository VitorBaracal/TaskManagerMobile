package com.example.taskmanagermobile.navigation

import kotlinx.serialization.Serializable

@Serializable
data object Login

@Serializable
data object Register

@Serializable
data object Home

@Serializable
data object Tasks

@Serializable
data class TaskForm(val id: Long = 0)

@Serializable
data object Subjects

@Serializable
data class SubjectForm(val id: Long = 0)

@Serializable
data object Notes

@Serializable
data class NoteForm(val id: Long = 0)

@Serializable
data object Users

@Serializable
data object NewUser

@Serializable
data class UserEdit(val id: Long)

@Serializable
data object Profile

@Serializable
data object Cep
