import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun HomeScreen(
    navController: NavController,
    eventViewModel: EventViewModel = viewModel()
) {
    val batchSize = 4 // Número de eventos que se cargan en cada paso
    var visibleEvents by remember { mutableStateOf(listOf<com.example.actividad1_eventsapp.model.Event>()) }

    val events = eventViewModel.events.observeAsState(initial = emptyList())
    val favoriteEvents = eventViewModel.favoriteEvents.observeAsState(initial = emptyList())
    val categories = listOf("Música", "Arte", "Deportes", "Tecnología", "Cultura") // Categorías de ejemplo

    // Cargar eventos iniciales
    LaunchedEffect(events.value) {
        if (events.value.isNotEmpty() && visibleEvents.isEmpty()) {
            visibleEvents = events.value.take(batchSize)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Eventos en tu ciudad",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Sección: "Eventos por categoría"
            Text(
                text = "Eventos por categoría",
                style = MaterialTheme.typography.titleSmall,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )

            // LazyRow con botones de categorías
            LazyRow(
                modifier = Modifier.padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    Button(
                        onClick = { /* Filtro por categoría */ },
                        shape = MaterialTheme.shapes.small
                    ) {
                        Text(text = category)
                    }
                }
            }

            Text(
                text = "Eventos destacados",
                style = MaterialTheme.typography.titleSmall,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )

            // Lista de eventos
            LazyColumn {
                itemsIndexed(visibleEvents) { index, event ->
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

                    // Verificar si se necesita cargar más eventos
                    if (index == visibleEvents.lastIndex && visibleEvents.size < events.value.size) {
                        LaunchedEffect(Unit) {
                            val nextBatch = events.value.drop(visibleEvents.size).take(batchSize)
                            visibleEvents = visibleEvents + nextBatch
                        }
                    }
                }
            }
        }
    }
}
