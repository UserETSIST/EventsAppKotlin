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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.actividad1_eventsapp.view.components.EventCard
import com.example.actividad1_eventsapp.viewmodel.EventViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    eventViewModel: EventViewModel = viewModel()
) {
    var query by remember { mutableStateOf(TextFieldValue("")) }
    val events = eventViewModel.events.observeAsState(initial = emptyList())
    val favoriteEvents = eventViewModel.favoriteEvents.observeAsState(initial = emptyList())

    // Filtrar eventos según el texto ingresado
    val filteredEvents = events.value.filter { event ->
        event.titulo.contains(query.text, ignoreCase = true) ||
                event.descripcion.contains(query.text, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Buscar Eventos") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Campo de búsqueda
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                label = { Text("Buscar...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de resultados
            if (filteredEvents.isEmpty()) {
                Text(
                    text = "No se encontraron resultados.",
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                LazyColumn {
                    items(filteredEvents) { event ->
                        val isFavorite = favoriteEvents.value.contains(event)
                        EventCard(
                            id = event.id,
                            titulo = event.titulo,
                            fInicio = event.fInicio,
                            imagen = event.imagen,
                            descripcion = event.descripcion,
                            isFavorite = isFavorite,
                            onClick = { id ->
                                navController.navigate("eventDetails/$id")
                            },
                            onFavoriteToggle = { favoriteId ->
                                eventViewModel.addFavorite(favoriteId)
                            }
                        )
                    }
                }
            }
        }
    }
}
