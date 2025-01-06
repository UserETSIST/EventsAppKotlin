package com.example.actividad1_eventsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.actividad1_eventsapp.model.Event
import com.example.actividad1_eventsapp.model.EventProvider

class EventViewModel : ViewModel() {

    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>> get() = _events

    private val _favoriteEvents = MutableLiveData<List<Event>>(emptyList())
    val favoriteEvents: LiveData<List<Event>> get() = _favoriteEvents

    init {
        refreshEvents()
    }

    fun refreshEvents() {
        _events.value = EventProvider().fetchEvents()
    }

    fun addFavorite(eventId: String) {
        val event = _events.value?.find { it.id == eventId }
        event?.let {
            val updatedFavorites = _favoriteEvents.value!!.toMutableList()
            if (updatedFavorites.contains(it)) {
                // Si el evento ya está en favoritos, se elimina
                updatedFavorites.remove(it)
                println("Evento eliminado de favoritos: ${it.titulo} (ID: ${it.id})")
            } else {
                // Si no está en favoritos, se añade
                updatedFavorites.add(it)
                println("Evento añadido a favoritos: ${it.titulo} (ID: ${it.id})")
            }
            _favoriteEvents.value = updatedFavorites

            // Imprimir la lista actualizada de favoritos
            println("Lista actualizada de favoritos:")
            updatedFavorites.forEach { favoriteEvent ->
                println("Favorito: ${favoriteEvent.titulo} (ID: ${favoriteEvent.id})")
            }
        } ?: run {
            println("Evento con ID $eventId no encontrado.")
        }
    }

}
