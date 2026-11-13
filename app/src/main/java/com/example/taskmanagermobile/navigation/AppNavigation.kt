package com.example.taskmanagermobile.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.example.taskmanagermobile.auth.UserSession
import com.example.taskmanagermobile.ui.screens.CepScreen
import com.example.taskmanagermobile.ui.screens.HomeScreen
import com.example.taskmanagermobile.ui.screens.LoginScreen
import com.example.taskmanagermobile.ui.screens.NoteFormScreen
import com.example.taskmanagermobile.ui.screens.NotesScreen
import com.example.taskmanagermobile.ui.screens.ProfileScreen
import com.example.taskmanagermobile.ui.screens.RegisterScreen
import com.example.taskmanagermobile.ui.screens.SubjectFormScreen
import com.example.taskmanagermobile.ui.screens.SubjectsScreen
import com.example.taskmanagermobile.ui.screens.TaskFormScreen
import com.example.taskmanagermobile.ui.screens.TasksScreen
import com.example.taskmanagermobile.ui.screens.UserEditScreen
import com.example.taskmanagermobile.ui.screens.UsersScreen

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    val currentEntry by navController.currentBackStackEntryAsState()
    val destination = currentEntry?.destination
    val userId = UserSession.userId

    LaunchedEffect(destination, userId) {
        val isPublic = destination?.hasRoute<Login>() == true ||
                destination?.hasRoute<Register>() == true

        if (destination != null && !isPublic && userId == null) {
            navController.navigate(Login) {
                popUpTo(0) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    NavHost(navController = navController, startDestination = Login, modifier = modifier) {
        composable<Login> {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Home) {
                        popUpTo(Login) { inclusive = true }
                    }
                },
                onRegister = { navController.navigate(Register) }
            )
        }

        composable<Register> {
            RegisterScreen(onBack = { navController.popBackStack() })
        }

        composable<Home> {
            HomeScreen(onNavigate = { route -> navController.navigate(route) })
        }

        composable<Tasks> {
            TasksScreen(
                onNew = { navController.navigate(TaskForm()) },
                onEdit = { id -> navController.navigate(TaskForm(id)) }
            )
        }

        composable<TaskForm> { entry ->
            TaskFormScreen(
                id = entry.toRoute<TaskForm>().id,
                onBack = { navController.popBackStack() }
            )
        }

        composable<Subjects> {
            SubjectsScreen(
                onNew = { navController.navigate(SubjectForm()) },
                onEdit = { id -> navController.navigate(SubjectForm(id)) }
            )
        }

        composable<SubjectForm> { entry ->
            SubjectFormScreen(
                id = entry.toRoute<SubjectForm>().id,
                onBack = { navController.popBackStack() }
            )
        }

        composable<Notes> {
            NotesScreen(
                onNew = { navController.navigate(NoteForm()) },
                onEdit = { id -> navController.navigate(NoteForm(id)) }
            )
        }

        composable<NoteForm> { entry ->
            NoteFormScreen(
                id = entry.toRoute<NoteForm>().id,
                onBack = { navController.popBackStack() }
            )
        }

        composable<Users> {
            UsersScreen(
                onNew = { navController.navigate(NewUser) },
                onEdit = { id -> navController.navigate(UserEdit(id)) }
            )
        }

        composable<NewUser> {
            RegisterScreen(onBack = { navController.popBackStack() })
        }

        composable<UserEdit> { entry ->
            UserEditScreen(
                id = entry.toRoute<UserEdit>().id,
                onBack = { navController.popBackStack() }
            )
        }

        composable<Profile> {
            ProfileScreen()
        }

        composable<Cep> {
            CepScreen()
        }
    }
}
