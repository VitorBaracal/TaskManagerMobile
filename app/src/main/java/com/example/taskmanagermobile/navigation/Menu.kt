package com.example.taskmanagermobile.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.taskmanagermobile.R
import com.example.taskmanagermobile.auth.UserSession

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Menu(navController: NavHostController) {
    TopAppBar(
        title = { Text("Task Manager") },
        actions = {
            TextButton(onClick = {
                navController.navigate(Home) {
                    popUpTo(Home)
                    launchSingleTop = true
                }
            }) {
                Text("Início")
            }

            IconButton(onClick = {
                UserSession.end()
                navController.navigate(Login) {
                    popUpTo(0) { inclusive = true }
                }
            }) {
                Icon(
                    painter = painterResource(R.drawable.ic_logout),
                    contentDescription = "Sair"
                )
            }
        }
    )
}
