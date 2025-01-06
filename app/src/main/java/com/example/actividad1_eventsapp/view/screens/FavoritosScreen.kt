package com.example.actividad1_eventsapp.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.actividad1_eventsapp.view.components.EventCard
import com.example.actividad1_eventsapp.viewmodel.EventViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritosScreen(
    navController: NavController,
    eventViewModel: EventViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Eventos Favoritos",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            val favoriteEvents = eventViewModel.favoriteEvents.observeAsState(initial = emptyList())
            println("Favorites in HomeScreen: ${favoriteEvents.value}")
            if (favoriteEvents.value.isEmpty()) {
                // Mensaje cuando no hay favoritos
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No tienes eventos favoritos aún.",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            } else {
                // Lista de eventos favoritos
                LazyColumn {
                    items(favoriteEvents.value) { event ->
                        EventCard(
                            id = event.id,
                            titulo = event.titulo,
                            fInicio = event.fInicio,
                            imagen = event.imagen,
                            descripcion = event.descripcion,
                            isFavorite = true, // Siempre true en FavoritosScreen
                            onClick = { id ->
                                // Navegar a los detalles del evento
                                navController.navigate("eventDetails/$id")
                            },
                            onFavoriteToggle = { favoriteId ->
                                // Eliminar de favoritos si se vuelve a pulsar
                                eventViewModel.addFavorite(favoriteId)
                            }
                        )
                    }
                }
            }
        }
    }
}
