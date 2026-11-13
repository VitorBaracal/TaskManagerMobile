package com.example.taskmanagermobile.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskmanagermobile.ui.viewmodel.TaskViewModel

@Composable
fun TaskFormScreen(
    id: Long,
    onBack: () -> Unit,
    viewModel: TaskViewModel = viewModel()
) {
    val statusOptions = listOf("Pendente", "Em andamento", "Concluída")

    LaunchedEffect(id) { viewModel.load(id) }
    LaunchedEffect(viewModel.saved) {
        if (viewModel.saved) onBack()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .imePadding()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            if (id == 0L) "Nova tarefa" else "Alterar tarefa",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = viewModel.title,
            onValueChange = { viewModel.title = it },
            label = { Text("Título") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = viewModel.dueDate,
            onValueChange = { if (it.length <= 10) viewModel.dueDate = it },
            label = { Text("Data de entrega (dd/mm/aaaa)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Text("Status")
        statusOptions.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.status = option }
            ) {
                RadioButton(
                    selected = viewModel.status == option,
                    onClick = { viewModel.status = option }
                )
                Text(option)
            }
        }

        viewModel.message?.let { Text(it, color = MaterialTheme.colorScheme.error) }

        Button(onClick = { viewModel.save(id) }, modifier = Modifier.fillMaxWidth()) {
            Text("Salvar")
        }

        TextButton(onClick = onBack) { Text("Cancelar") }
    }
}
