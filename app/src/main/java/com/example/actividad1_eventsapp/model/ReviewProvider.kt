package com.example.actividad1_eventsapp.model

object ReviewProvider {
    private val reviews = mutableListOf<Review>()

    fun addReview(review: Review) {
        reviews.add(review)
    }

    fun getReviewsForEvent(eventId: Int): List<Review> {
        return reviews.filter { it.eventId == eventId }
    }
}
