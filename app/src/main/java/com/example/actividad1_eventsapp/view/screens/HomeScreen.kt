import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.actividad1_eventsapp.view.components.EventCard
import com.example.actividad1_eventsapp.viewmodel.EventViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    eventViewModel: EventViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Eventos en tu ciudad",
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
            // Subtítulo: Buscar por categoría
            Text(
                text = "Buscar por categoría",
                style = MaterialTheme.typography.bodySmall,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Subtítulo: Eventos Destacados
            Text(
                text = "Eventos Destacados",
                style = MaterialTheme.typography.bodySmall,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de eventos
            val events = eventViewModel.events.observeAsState(initial = emptyList())
            LazyColumn {
                items(events.value) { event ->
                    val isFavorite = eventViewModel.favoriteEvents.value?.contains(event) == true
                    EventCard(
                        id = event.id,
                        titulo = event.titulo,
                        fInicio = event.fInicio,
                        imagen = event.imagen,
                        descripcion = event.descripcion,
                        isFavorite = isFavorite,
                        onClick = { id ->
                            // Navegar a detalles del evento
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