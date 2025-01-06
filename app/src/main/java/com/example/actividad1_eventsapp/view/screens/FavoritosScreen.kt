package com.example.actividad1_eventsapp.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
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
    var query by remember { mutableStateOf(TextFieldValue("")) }
    val favoriteEvents = eventViewModel.favoriteEvents.observeAsState(initial = emptyList())

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
            // Campo de búsqueda
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                label = { Text("Buscar en favoritos...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            // Filtrar eventos según el texto ingresado
            val filteredEvents = favoriteEvents.value.filter { event ->
                event.titulo.contains(query.text, ignoreCase = true) ||
                        event.descripcion.contains(query.text, ignoreCase = true)
            }

            if (filteredEvents.isEmpty()) {
                // Mensaje cuando no hay favoritos o no se encuentran resultados
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No se encontraron resultados.",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            } else {
                // Lista de eventos favoritos filtrados
                LazyColumn {
                    items(filteredEvents) { event ->
                        EventCard(
                            id = event.id,
                            titulo = event.titulo,
                            fInicio = event.fInicio,
                            imagen = event.imagen,
                            descripcion = event.descripcion,
                            isFavorite = true,
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
