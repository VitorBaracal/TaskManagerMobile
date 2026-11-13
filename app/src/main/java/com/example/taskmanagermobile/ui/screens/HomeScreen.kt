package com.example.taskmanagermobile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskmanagermobile.navigation.Cep
import com.example.taskmanagermobile.navigation.Notes
import com.example.taskmanagermobile.navigation.Profile
import com.example.taskmanagermobile.navigation.Subjects
import com.example.taskmanagermobile.navigation.Tasks
import com.example.taskmanagermobile.navigation.Users

@Composable
fun HomeScreen(onNavigate: (Any) -> Unit) {
    val options = listOf(
        "Tarefas" to Tasks,
        "Matérias" to Subjects,
        "Anotações" to Notes,
        "Usuários" to Users,
        "Perfil" to Profile,
        "Consultar CEP" to Cep
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Início", style = MaterialTheme.typography.headlineMedium)

        options.forEach { (label, route) ->
            Button(onClick = { onNavigate(route) }, modifier = Modifier.fillMaxWidth()) {
                Text(label)
            }
        }
    }
}
