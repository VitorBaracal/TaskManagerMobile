package com.example.taskmanagermobile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
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
import com.example.taskmanagermobile.ui.viewmodel.SubjectViewModel

@Composable
fun SubjectFormScreen(
    id: Long,
    onBack: () -> Unit,
    viewModel: SubjectViewModel = viewModel()
) {
    LaunchedEffect(id) { viewModel.load(id) }
    LaunchedEffect(viewModel.saved) {
        if (viewModel.saved) onBack()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            if (id == 0L) "Nova matéria" else "Alterar matéria",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = viewModel.name,
            onValueChange = { viewModel.name = it },
            label = { Text("Nome") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = viewModel.teacher,
            onValueChange = { viewModel.teacher = it },
            label = { Text("Professor") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        viewModel.message?.let { Text(it, color = MaterialTheme.colorScheme.error) }

        Button(onClick = { viewModel.save(id) }, modifier = Modifier.fillMaxWidth()) {
            Text("Salvar")
        }

        TextButton(onClick = onBack) { Text("Cancelar") }
    }
}
