package com.example.taskmanagermobile.pages

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
fun Login(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

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
            .padding(horizontal = 24.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        Surface(
            modifier = Modifier.size(72.dp),
            shape = RoundedCornerShape(20.dp),
            color = BrandPurple
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    painter = painterResource(R.drawable.ic_check_all),
                    contentDescription = null,
                    modifier = Modifier.size(34.dp),
                    tint = Color.White
                )
            }
        }

        Text(
            text = "Task Manager",
            modifier = Modifier.padding(top = 20.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TitleText
        )

        Text(
            text = "Sua rotina acadêmica organizada",
            modifier = Modifier.padding(top = 4.dp),
            fontSize = 14.sp,
            color = MutedText
        )

        Spacer(modifier = Modifier.height(36.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            FieldLabel(text = "E-mail")
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text(text = "seu.nome@email.com", color = MutedText) },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_email),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                shape = RoundedCornerShape(12.dp),
                colors = textFieldColors,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            FieldLabel(text = "Senha")
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text(text = "Digite sua senha", color = MutedText) },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_lock),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                },
                trailingIcon = {
                    val visibilityIcon = if (passwordVisible) {
                        R.drawable.ic_passwordvisibility
                    } else {
                        R.drawable.ic_passwordnovisibility
                    }

                    val description = if (passwordVisible) {
                        "Ocultar senha"
                    } else {
                        "Mostrar senha"
                    }

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = painterResource(visibilityIcon),
                            contentDescription = description,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                visualTransformation = if (passwordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                shape = RoundedCornerShape(12.dp),
                colors = textFieldColors,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

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
                text = "Entrar",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        TextButton(
            onClick = { },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(
                text = "Esqueceu sua senha?",
                fontSize = 14.sp,
                color = BrandPurple
            )
        }
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
fun LoginPreview() {
    TaskManagerMobileTheme {
        Login()
    }
}
