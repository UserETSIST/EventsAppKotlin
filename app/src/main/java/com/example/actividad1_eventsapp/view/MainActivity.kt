package com.example.actividad1_eventsapp.view

import HomeScreen
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.actividad1_eventsapp.navigation.Screen
import com.example.actividad1_eventsapp.ui.theme.Actividad1_EventsAppTheme
import com.example.actividad1_eventsapp.view.components.BottomNavigationBar
import com.example.actividad1_eventsapp.view.screens.EventDetailScreen
import com.example.actividad1_eventsapp.view.screens.FavoritosScreen
import com.example.actividad1_eventsapp.view.screens.NuevoScreen
import com.example.actividad1_eventsapp.viewmodel.EventViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Actividad1_EventsAppTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavigationBar(navController) }
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
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { HomeScreen(navController = navController, eventViewModel = eventViewModel) }
        composable(Screen.Nuevo.route) { NuevoScreen(navController) }
        composable(Screen.Favoritos.route) { FavoritosScreen(navController = navController, eventViewModel = eventViewModel) }

        composable(
            route = "eventDetails/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getString("id") ?: ""
            EventDetailScreen(navController = navController, id = eventId)
        }

    }

}
