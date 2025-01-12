package com.example.actividad1_eventsapp.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.actividad1_eventsapp.model.Review
import com.example.actividad1_eventsapp.model.ReviewProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReviewScreen(
    navController: NavController,
    eventId: Int
) {
    var reviewText by remember { mutableStateOf("") }
    var reviewerName by remember { mutableStateOf("") }
    val reviews = remember { mutableStateOf(ReviewProvider.getReviewsForEvent(eventId)) }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "Añadir Reseña",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // List of existing reviews
            Text(
                text = "Reseñas actuales:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .weight(1f)
            ) {
                items(reviews.value) { review ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(
                                text = "Nombre: ${review.reviewerName}",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = review.reviewText)
                        }
                    }
                }
            }

            // Input for reviewer's name
            OutlinedTextField(
                value = reviewerName,
                onValueChange = { reviewerName = it },
                label = { Text("Tu Nombre") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Input for review text
            OutlinedTextField(
                value = reviewText,
                onValueChange = { reviewText = it },
                label = { Text("Escribe tu reseña aquí") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Save review button
            Button(
                onClick = {
                    if (reviewText.isNotBlank() && reviewerName.isNotBlank()) {
                        val newReview = Review(
                            eventId = eventId,
                            reviewText = reviewText,
                            reviewerName = reviewerName
                        )
                        ReviewProvider.addReview(newReview)
                        reviews.value = ReviewProvider.getReviewsForEvent(eventId)
                        reviewText = ""
                        reviewerName = ""
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Guardar Reseña")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Back button
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                )
            ) {
                Text("Cancelar")
            }
        }
    }
}
