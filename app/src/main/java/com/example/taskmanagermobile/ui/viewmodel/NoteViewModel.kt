package com.example.taskmanagermobile.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanagermobile.data.dal.NoteDao
import com.example.taskmanagermobile.data.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = NoteDao(application)

    var notes by mutableStateOf<List<Note>>(emptyList())
        private set
    var title by mutableStateOf("")
    var content by mutableStateOf("")
    var saved by mutableStateOf(false)
        private set
    var message by mutableStateOf<String?>(null)
        private set

    fun list() {
        viewModelScope.launch {
            notes = withContext(Dispatchers.IO) { dao.list() }
        }
    }

    fun load(id: Long) {
        if (id == 0L) return
        viewModelScope.launch {
            val note = withContext(Dispatchers.IO) { dao.findById(id) } ?: return@launch
            title = note.title
            content = note.content
        }
    }

    fun save(id: Long) {
        if (title.isBlank() || content.isBlank()) {
            message = "Preencha todos os campos."
            return
        }

        viewModelScope.launch {
            val note = Note(id, title.trim(), content.trim())
            withContext(Dispatchers.IO) {
                if (id == 0L) dao.insert(note) else dao.update(note)
            }
            saved = true
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { dao.delete(id) }
            notes = notes.filterNot { it.id == id }
        }
    }
}
