package com.example.actividad1_eventsapp.model

data class ApiResponse(
    val error: Boolean,
    val message: String,
    val registros: List<Event>
)


data class Event(
    val ID: Int,
    val ID_TEVE: Int,
    val TITULO: String,
    val UBICACION: String,
    val ESTADO: String?,
    val DIRECCION: String?,
    val ENTGRATUITA: String?,
    val DESTACADO: Int?,
    val FINICIO: String,
    val FFINAL: String?,
    val DESCRIPCION: String,
    val IMAGEN: String,
    val CONTACTO: String?,
    val NOCONTACTO: String?,
    val EMAILCONT: String?
)

