package com.example.actividad1_eventsapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Add

sealed class Screen(val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object Home : Screen("home", Icons.Default.Home)
    object Nuevo : Screen("nuevo", Icons.Default.Add)
    object Favoritos : Screen("favoritos", Icons.Default.Favorite)
}

