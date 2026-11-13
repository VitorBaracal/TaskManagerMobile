package com.example.taskmanagermobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.taskmanagermobile.navigation.AppNavigation
import com.example.taskmanagermobile.navigation.Login
import com.example.taskmanagermobile.navigation.Menu
import com.example.taskmanagermobile.navigation.Register
import com.example.taskmanagermobile.ui.theme.TaskManagerMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskManagerMobileTheme {
                val navController = rememberNavController()
                val currentEntry by navController.currentBackStackEntryAsState()
                val destination = currentEntry?.destination

                val showMenu = destination != null &&
                        !destination.hasRoute<Login>() &&
                        !destination.hasRoute<Register>()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        if (showMenu) Menu(navController = navController)
                    }
                ) { innerPadding ->
                    AppNavigation(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
