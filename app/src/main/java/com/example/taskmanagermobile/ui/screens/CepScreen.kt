package com.example.taskmanagermobile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskmanagermobile.ui.viewmodel.CepViewModel

@Composable
fun CepScreen(viewModel: CepViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Consultar CEP", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = viewModel.cep,
            onValueChange = viewModel::changeCep,
            label = { Text("CEP") },
            singleLine = true,
            enabled = !viewModel.loading,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = viewModel::search, enabled = !viewModel.loading) {
            Text("Consultar")
        }

        if (viewModel.loading) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())

        viewModel.message?.let { Text(it) }

        viewModel.address?.let { address ->
            Text("CEP: ${address.cep.orEmpty()}")
            Text("Rua: ${address.street.orEmpty()}")
            Text("Bairro: ${address.district.orEmpty()}")
            Text("Cidade: ${address.city.orEmpty()} - ${address.state.orEmpty()}")
        }
    }
}
