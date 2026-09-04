package com.example.taskmanagermobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.taskmanagermobile.pages.CreateTask
import com.example.taskmanagermobile.pages.HomePage
import com.example.taskmanagermobile.pages.Login
import com.example.taskmanagermobile.pages.Profile
import com.example.taskmanagermobile.ui.theme.TaskManagerMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskManagerMobileTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {
    val page = "Profile"

    when (page) {
        "Login" -> Login(modifier = modifier)
        "HomePage" -> HomePage(modifier = modifier)
        "CreateTask" -> CreateTask(modifier = modifier)
        "Profile" -> Profile(modifier = modifier)
    }
}
