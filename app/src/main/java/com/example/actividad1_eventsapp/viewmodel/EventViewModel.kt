package com.example.actividad1_eventsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.actividad1_eventsapp.model.Event
import com.example.actividad1_eventsapp.model.EventProvider

class EventViewModel : ViewModel() {

    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>> get() = _events

    init {
        refreshEvents()
    }

    fun refreshEvents() {
        _events.value = EventProvider().fetchEvents()
    }

}