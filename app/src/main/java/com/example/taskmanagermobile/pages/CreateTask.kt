package com.example.taskmanagermobile.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun CreateTask(modifier: Modifier = Modifier) {
    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = CardBackground,
        unfocusedContainerColor = CardBackground,
        disabledContainerColor = CardBackground,
        focusedBorderColor = BorderGray,
        unfocusedBorderColor = BorderGray,
        disabledBorderColor = BorderGray,
        disabledTextColor = TitleText
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CardBackground)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        Header()

        Spacer(modifier = Modifier.height(28.dp))

        FieldLabel(text = "Nome da Tarefa", required = true)
        OutlinedTextField(
            value = "",
            onValueChange = { },
            readOnly = true,
            placeholder = {
                Text(
                    text = "Ex: Entregar relatório de física",
                    color = MutedText
                )
            },
            shape = RoundedCornerShape(12.dp),
            colors = textFieldColors,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(18.dp))

        FieldLabel(text = "Status")
        OutlinedTextField(
            value = "Pendente",
            onValueChange = { },
            readOnly = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_bolt),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = TitleText
                )
            },
            shape = RoundedCornerShape(12.dp),
            colors = textFieldColors,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(18.dp))

        FieldLabel(text = "Prioridade")
        OutlinedTextField(
            value = "Alta",
            onValueChange = { },
            readOnly = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_flag),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = TitleText
                )
            },
            shape = RoundedCornerShape(12.dp),
            colors = textFieldColors,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(18.dp))

        FieldLabel(text = "Data Fim")
        OutlinedTextField(
            value = "20/09/2026",
            onValueChange = { },
            readOnly = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_calendar),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = TitleText
                )
            },
            shape = RoundedCornerShape(12.dp),
            colors = textFieldColors,
            modifier = Modifier.fillMaxWidth()
        )

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
                containerColor = PageBackground,
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
            color = PageBackground
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

@Preview(showBackground = true)
@Composable
fun CreateTaskPreview() {
    TaskManagerMobileTheme {
        CreateTask()
    }
}
