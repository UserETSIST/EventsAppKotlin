package com.example.actividad1_eventsapp.view.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil3.request.crossfade
import com.example.actividad1_eventsapp.R

@Composable
fun EventCard(
    id: String,
    titulo: String,
    fInicio: String,
    imagen: String?,
    descripcion: String,
    isFavorite: Boolean,
    onClick: (String) -> Unit,
    onFavoriteToggle: (String) -> Unit
) {
    // Animación para el tamaño del botón de favoritos
    val scale by animateFloatAsState(
        targetValue = if (isFavorite) 1.3f else 1f,
        animationSpec = tween(durationMillis = 300)
    )

    // Animación para el color del botón de favoritos
    val starColor by animateColorAsState(
        targetValue = if (isFavorite) Color.Yellow else MaterialTheme.colorScheme.onSurface,
        animationSpec = tween(durationMillis = 300)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
            contentColor = Color.White // Forzar blanco para todos los textos por defecto
        ),
        onClick = { onClick(id) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen del evento
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imagen ?: "https://via.placeholder.com/150")
                    .crossfade(true)
                    .build(),
                contentDescription = descripcion,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 8.dp)
            )

            // Información del evento
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = titulo,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White // Forzar blanco para el título
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Inicio: $fInicio",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.8f) // Blanco con opacidad
                )
            }

            // Botón de Favoritos
            IconButton(
                onClick = { onFavoriteToggle(id) },
                modifier = Modifier.scale(scale)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = if (isFavorite) "Quitar de favoritos" else "Añadir a favoritos",
                    tint = starColor,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
