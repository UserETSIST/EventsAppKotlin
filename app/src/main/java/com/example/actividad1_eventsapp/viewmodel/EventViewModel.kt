package com.example.actividad1_eventsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.actividad1_eventsapp.model.ApiResponse
import com.example.actividad1_eventsapp.model.Event
import com.example.actividad1_eventsapp.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Response

class EventViewModel : ViewModel() {

    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>> get() = _events

    private val _favoriteEvents = MutableLiveData<List<Event>>(emptyList())
    val favoriteEvents: LiveData<List<Event>> get() = _favoriteEvents

     init {
         print("ENTRO EN INIT")
         viewModelScope.launch {
             refreshEvents()
         }
    }

    private suspend fun refreshEvents() {
        println("Entro en refreshEvents()")
        try {
            println("Entro en el TRY")
            val response: Response<ApiResponse> = RetrofitInstance.api.getEvents()
            println("RESPUESTAAA1234: ${response.body()}")
            if (response.isSuccessful) {
                val apiResponse = response.body()
                _events.value = apiResponse?.registros ?: emptyList()
                println("TODOS EVENTOS: ${_events.value}")
                println("EVENTS API SUCCESS: ${response.body()}")
            } else {
                println("API ERROR: ${response.code()} - ${response.message()}")
                _events.value = emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()

            println("EXCEPTION: ${e.message}")
            _events.value = emptyList()
        }
    }


    fun addFavorite(eventId: Int) {
        val event = _events.value?.find { it.ID == eventId }
        event?.let {
            val updatedFavorites = _favoriteEvents.value!!.toMutableList()
            if (updatedFavorites.contains(it)) {
                // Si el evento ya está en favoritos, se elimina
                updatedFavorites.remove(it)
                println("Evento eliminado de favoritos: ${it.TITULO} (ID: ${it.ID})")
            } else {
                // Si no está en favoritos, se añade
                updatedFavorites.add(it)
                println("Evento añadido a favoritos: ${it.TITULO} (ID: ${it.ID})")
            }
            _favoriteEvents.value = updatedFavorites

            // Imprimir la lista actualizada de favoritos
            println("Lista actualizada de favoritos:")
            updatedFavorites.forEach { favoriteEvent ->
                println("Favorito: ${favoriteEvent.TITULO} (ID: ${favoriteEvent.ID})")
            }
        } ?: run {
            println("Evento con ID $eventId no encontrado.")
        }
    }

    fun getEventByID(id: Int): Event? {

        println("Available Events: ${_events.value}")


        return _events.value?.find { it.ID == id }
    }

}
