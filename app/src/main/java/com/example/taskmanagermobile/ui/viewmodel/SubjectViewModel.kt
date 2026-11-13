package com.example.taskmanagermobile.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanagermobile.data.dal.SubjectDao
import com.example.taskmanagermobile.data.model.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SubjectViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = SubjectDao(application)

    var subjects by mutableStateOf<List<Subject>>(emptyList())
        private set
    var name by mutableStateOf("")
    var teacher by mutableStateOf("")
    var saved by mutableStateOf(false)
        private set
    var message by mutableStateOf<String?>(null)
        private set

    fun list() {
        viewModelScope.launch {
            subjects = withContext(Dispatchers.IO) { dao.list() }
        }
    }

    fun load(id: Long) {
        if (id == 0L) return
        viewModelScope.launch {
            val subject = withContext(Dispatchers.IO) { dao.findById(id) } ?: return@launch
            name = subject.name
            teacher = subject.teacher
        }
    }

    fun save(id: Long) {
        if (name.isBlank() || teacher.isBlank()) {
            message = "Preencha todos os campos."
            return
        }

        viewModelScope.launch {
            val subject = Subject(id, name.trim(), teacher.trim())
            withContext(Dispatchers.IO) {
                if (id == 0L) dao.insert(subject) else dao.update(subject)
            }
            saved = true
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { dao.delete(id) }
            subjects = subjects.filterNot { it.id == id }
        }
    }
}
