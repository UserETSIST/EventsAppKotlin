package com.example.actividad1_eventsapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search


sealed class Screen(val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object Home : Screen("inicio", Icons.Default.Home)
    object Search : Screen("buscar", Icons.Default.Search)
    object Favoritos : Screen("favoritos", Icons.Default.Favorite)
}

