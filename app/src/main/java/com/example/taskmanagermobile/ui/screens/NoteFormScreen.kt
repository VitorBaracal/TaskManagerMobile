package com.example.taskmanagermobile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskmanagermobile.ui.viewmodel.NoteViewModel

@Composable
fun NoteFormScreen(
    id: Long,
    onBack: () -> Unit,
    viewModel: NoteViewModel = viewModel()
) {
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
            if (id == 0L) "Nova anotação" else "Alterar anotação",
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
            value = viewModel.content,
            onValueChange = { viewModel.content = it },
            label = { Text("Conteúdo") },
            minLines = 4,
            modifier = Modifier.fillMaxWidth()
        )

        viewModel.message?.let { Text(it, color = MaterialTheme.colorScheme.error) }

        Button(onClick = { viewModel.save(id) }, modifier = Modifier.fillMaxWidth()) {
            Text("Salvar")
        }

        TextButton(onClick = onBack) { Text("Cancelar") }
    }
}
