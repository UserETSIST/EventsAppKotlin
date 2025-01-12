package com.example.actividad1_eventsapp.view

import HomeScreen
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.actividad1_eventsapp.R
import com.example.actividad1_eventsapp.navigation.Screen
import com.example.actividad1_eventsapp.ui.theme.Actividad1_EventsAppTheme
import com.example.actividad1_eventsapp.view.components.BottomNavigationBar
import com.example.actividad1_eventsapp.view.screens.EventDetailScreen
import com.example.actividad1_eventsapp.view.screens.FavoritosScreen
import com.example.actividad1_eventsapp.view.screens.SearchScreen
import com.example.actividad1_eventsapp.viewmodel.EventViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkTheme by remember { mutableStateOf(false) } // Control de tema

            Actividad1_EventsAppTheme(darkTheme = isDarkTheme) {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                    bottomBar = { BottomNavigationBar(navController) },
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Eventos",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            },
                            actions = {
                                // Botón de alternar tema
                                IconButton(onClick = { isDarkTheme = !isDarkTheme }) {
                                    Icon(
                                        painter = if (isDarkTheme) painterResource(id = R.drawable.ic_sun)
                                        else painterResource(id = R.drawable.ic_moon),
                                        contentDescription = "Alternar Tema",
                                        tint = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                ) {
                    AppNavHost(navController = navController)
                }
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController) {
    val eventViewModel: EventViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController,
                eventViewModel = eventViewModel
            )
        }

        composable(Screen.Search.route) {
            SearchScreen(
                navController = navController,
                eventViewModel = eventViewModel
            )
        }

        composable(Screen.Favoritos.route) {
            FavoritosScreen(
                navController = navController,
                eventViewModel = eventViewModel
            )
        }

        composable(
            route = "eventDetails/{ID}",
            arguments = listOf(navArgument("ID") { type = NavType.IntType })
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getInt("ID")
            println("EVENTID: $eventId")
            if (eventId != null) {
                EventDetailScreen(
                    navController = navController,
                    id = eventId
                )
            }
        }
    }
}
