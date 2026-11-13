package com.example.taskmanagermobile.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanagermobile.data.dal.TaskDao
import com.example.taskmanagermobile.data.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = TaskDao(application)

    var tasks by mutableStateOf<List<Task>>(emptyList())
        private set
    var title by mutableStateOf("")
    var status by mutableStateOf("Pendente")
    var dueDate by mutableStateOf("")
    var saved by mutableStateOf(false)
        private set
    var message by mutableStateOf<String?>(null)
        private set

    fun list() {
        viewModelScope.launch {
            tasks = withContext(Dispatchers.IO) { dao.list() }
        }
    }

    fun load(id: Long) {
        if (id == 0L) return
        viewModelScope.launch {
            val task = withContext(Dispatchers.IO) { dao.findById(id) } ?: return@launch
            title = task.title
            status = task.status
            dueDate = task.dueDate
        }
    }

    fun save(id: Long) {
        if (title.isBlank() || dueDate.isBlank()) {
            message = "Preencha todos os campos."
            return
        }

        viewModelScope.launch {
            val task = Task(id, title.trim(), status, dueDate.trim())
            withContext(Dispatchers.IO) {
                if (id == 0L) dao.insert(task) else dao.update(task)
            }
            saved = true
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { dao.delete(id) }
            tasks = tasks.filterNot { it.id == id }
        }
    }
}
