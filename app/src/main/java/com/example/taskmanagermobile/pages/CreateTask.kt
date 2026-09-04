package com.example.taskmanagermobile.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanagermobile.R
import com.example.taskmanagermobile.ui.theme.BorderGray
import com.example.taskmanagermobile.ui.theme.BrandPurple
import com.example.taskmanagermobile.ui.theme.CardBackground
import com.example.taskmanagermobile.ui.theme.HighContent
import com.example.taskmanagermobile.ui.theme.MutedText
import com.example.taskmanagermobile.ui.theme.PageBackground
import com.example.taskmanagermobile.ui.theme.TaskManagerMobileTheme
import com.example.taskmanagermobile.ui.theme.TitleText
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val statusOptions = listOf("Pendente", "Em andamento", "Concluída")
private val priorityOptions = listOf("Alta", "Média", "Baixa")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTask(modifier: Modifier = Modifier) {
    var taskName by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("Pendente") }
    var priority by remember { mutableStateOf("Alta") }
    var endDate by remember { mutableStateOf("20/09/2026") }
    var showCalendar by remember { mutableStateOf(false) }

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = CardBackground,
        unfocusedContainerColor = CardBackground,
        disabledContainerColor = CardBackground,
        focusedBorderColor = BrandPurple,
        unfocusedBorderColor = BorderGray,
        disabledBorderColor = BorderGray,
        focusedTextColor = TitleText,
        unfocusedTextColor = TitleText,
        disabledTextColor = TitleText,
        focusedLeadingIconColor = MutedText,
        unfocusedLeadingIconColor = MutedText,
        disabledLeadingIconColor = MutedText,
        focusedTrailingIconColor = MutedText,
        unfocusedTrailingIconColor = MutedText,
        cursorColor = BrandPurple
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(PageBackground)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        Header()

        Spacer(modifier = Modifier.height(28.dp))

        FieldLabel(text = "Nome da Tarefa", required = true)
        OutlinedTextField(
            value = taskName,
            onValueChange = { taskName = it },
            placeholder = {
                Text(
                    text = "Ex: Entregar relatório de física",
                    color = MutedText
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = textFieldColors,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(18.dp))

        FieldLabel(text = "Status")
        DropdownField(
            value = status,
            options = statusOptions,
            icon = R.drawable.ic_bolt,
            colors = textFieldColors,
            onOptionSelected = { status = it }
        )

        Spacer(modifier = Modifier.height(18.dp))

        FieldLabel(text = "Prioridade")
        DropdownField(
            value = priority,
            options = priorityOptions,
            icon = R.drawable.ic_flag,
            colors = textFieldColors,
            onOptionSelected = { priority = it }
        )

        Spacer(modifier = Modifier.height(18.dp))

        FieldLabel(text = "Data Fim")
        OutlinedTextField(
            value = endDate,
            onValueChange = { },
            readOnly = true,
            enabled = false,
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_calendar),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            },
            shape = RoundedCornerShape(12.dp),
            colors = textFieldColors,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showCalendar = true }
        )

        if (showCalendar) {
            val datePickerState = rememberDatePickerState()

            DatePickerDialog(
                onDismissRequest = { showCalendar = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            endDate = datePickerState.selectedDateMillis?.let { millis ->
                                SimpleDateFormat(
                                    "dd/MM/yyyy",
                                    Locale.getDefault()
                                ).format(Date(millis))
                            } ?: endDate
                            showCalendar = false
                        }
                    ) {
                        Text(text = "Confirmar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showCalendar = false }) {
                        Text(text = "Cancelar")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BrandPurple,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Criar",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, BorderGray),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = CardBackground,
                contentColor = TitleText
            )
        ) {
            Text(
                text = "Cancelar",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Surface(
            modifier = Modifier.size(40.dp),
            shape = CircleShape,
            color = CardBackground,
            border = BorderStroke(1.dp, BorderGray)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = "Voltar",
                    modifier = Modifier.size(20.dp),
                    tint = TitleText
                )
            }
        }

        Text(
            text = "Nova Tarefa",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = TitleText
        )
    }
}

@Composable
private fun FieldLabel(text: String, required: Boolean = false) {
    Row(modifier = Modifier.padding(bottom = 8.dp)) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = TitleText
        )
        if (required) {
            Text(
                text = " *",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = HighContent
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DropdownField(
    value: String,
    options: List<String>,
    icon: Int,
    colors: TextFieldColors,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { },
            readOnly = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            shape = RoundedCornerShape(12.dp),
            colors = colors,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(type = ExposedDropdownMenuAnchorType.PrimaryNotEditable)
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateTaskPreview() {
    TaskManagerMobileTheme {
        CreateTask()
    }
}
