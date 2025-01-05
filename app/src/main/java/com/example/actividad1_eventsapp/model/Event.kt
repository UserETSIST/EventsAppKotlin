package com.example.actividad1_eventsapp.model

data class Event(
    val id: String,
    val idTeve: Int,
    val titulo: String,
    val ubicacion: String,
    val direccion: String,
    val entGratuita: String,
    val fInicio: String,
    val fFinal: String,
    val descripcion: String,
    val imagen: String,
    val contacto: String,
    val noContacto: String,
    val emailCont: String,
    val estado: String,
    val destacado: Int? // Puede ser null
)
