package com.example.taskmanagermobile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskmanagermobile.ui.viewmodel.TaskViewModel

@Composable
fun TasksScreen(
    onNew: () -> Unit,
    onEdit: (Long) -> Unit,
    viewModel: TaskViewModel = viewModel()
) {
    var deleteId by remember { mutableStateOf<Long?>(null) }

    LaunchedEffect(Unit) { viewModel.list() }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Tarefas", style = MaterialTheme.typography.headlineMedium)
                Button(onClick = onNew) { Text("Nova tarefa") }
                if (viewModel.tasks.isEmpty()) Text("Nenhuma tarefa cadastrada.")
            }
        }

        items(viewModel.tasks, key = { it.id }) { task ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(task.title, style = MaterialTheme.typography.titleMedium)
                    Text("${task.status} • Entrega: ${task.dueDate}")
                    Row {
                        TextButton(onClick = { onEdit(task.id) }) { Text("Alterar") }
                        TextButton(onClick = { deleteId = task.id }) { Text("Excluir") }
                    }
                }
            }
        }
    }

    val selected = viewModel.tasks.firstOrNull { it.id == deleteId }
    if (selected != null) {
        AlertDialog(
            onDismissRequest = { deleteId = null },
            title = { Text("Excluir tarefa?") },
            text = { Text("Excluir \"${selected.title}\"?") },
            confirmButton = {
                TextButton(onClick = {
                    deleteId = null
                    viewModel.delete(selected.id)
                }) { Text("Excluir") }
            },
            dismissButton = {
                TextButton(onClick = { deleteId = null }) { Text("Cancelar") }
            }
        )
    }
}
