package com.example.actividad1_eventsapp.model


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class EventProvider {

    private val _events = MutableLiveData<List<Event>>()
    val eventsAPI: LiveData<List<Event>> get() = _events


}


// val events = listOf(
// Event(
// id = "1",
// idTeve = 1001,
// titulo = "Festival de Música",
// ubicacion = "Parque Central",
// direccion = "Calle 123, Ciudad",
// entGratuita = "Sí",
// fInicio = "2025-01-10",
// fFinal = "2025-01-12",
// descripcion = "Un festival de música con bandas locales e internacionales.",
// imagen = "https://via.placeholder.com/150",
// contacto = "123456789",
// noContacto = "987654321",
// emailCont = "contacto@festival.com",
// estado = "Activo",
// destacado = 1
// ),
// Event(
// id = "2",
// idTeve = 1002,
// titulo = "Exposición de Arte",
// ubicacion = "Museo Nacional",
// direccion = "Avenida de las Artes, Ciudad",
// entGratuita = "No",
// fInicio = "2025-02-01",
// fFinal = "2025-02-28",
// descripcion = "Exposición de arte contemporáneo con artistas nacionales.",
// imagen = "https://via.placeholder.com/150",
// contacto = "112233445",
// noContacto = "998877665",
// emailCont = "info@museo.com",
// estado = "Activo",
// destacado = null
// ),
// Event(
// id = "3",
// idTeve = 1003,
// titulo = "Torneo de Ajedrez",
// ubicacion = "Club Deportivo",
// direccion = "Calle Estratégica 5, Ciudad",
// entGratuita = "Sí",
// fInicio = "2025-03-15",
// fFinal = "2025-03-16",
// descripcion = "Torneo para jugadores de todos los niveles. Inscripción gratuita.",
// imagen = "https://via.placeholder.com/150",
// contacto = "334455667",
// noContacto = "776655443",
// emailCont = "info@clubdeportivo.com",
// estado = "Activo",
// destacado = 1
// ),
// Event(
// id = "4",
// idTeve = 1004,
// titulo = "Feria del Libro",
// ubicacion = "Centro de Convenciones",
// direccion = "Av. Cultura 45, Ciudad",
// entGratuita = "No",
// fInicio = "2025-04-20",
// fFinal = "2025-04-25",
// descripcion = "Feria con escritores y editoriales reconocidas.",
// imagen = "https://via.placeholder.com/150",
// contacto = "998877665",
// noContacto = "334455667",
// emailCont = "info@ferialibro.com",
// estado = "Activo",
// destacado = null
// ),
// Event(
// id = "5",
// idTeve = 1005,
// titulo = "Carrera Solidaria",
// ubicacion = "Plaza Mayor",
// direccion = "Calle Principal 1, Ciudad",
// entGratuita = "Sí",
// fInicio = "2025-05-01",
// fFinal = "2025-05-01",
// descripcion = "Carrera de 10 km a beneficio de organizaciones locales.",
// imagen = "https://via.placeholder.com/150",
// contacto = "556677889",
// noContacto = "889977665",
// emailCont = "info@carrerasolidaria.com",
// estado = "Activo",
// destacado = 1
// ),
// Event(
// id = "6",
// idTeve = 1006,
// titulo = "Festival Gastronómico",
// ubicacion = "Plaza Mayor",
// direccion = "Calle Sabores 8, Ciudad",
// entGratuita = "Sí",
// fInicio = "2025-06-01",
// fFinal = "2025-06-03",
// descripcion = "Prueba platos de diferentes culturas y disfruta de talleres de cocina.",
// imagen = "https://via.placeholder.com/150",
// contacto = "665544332",
// noContacto = "221133445",
// emailCont = "gastro@festivales.com",
// estado = "Activo",
// destacado = null
// ),
// Event(
// id = "7",
// idTeve = 1007,
// titulo = "Obra de Teatro Clásico",
// ubicacion = "Teatro Nacional",
// direccion = "Avenida de las Artes 12, Ciudad",
// entGratuita = "No",
// fInicio = "2025-07-15",
// fFinal = "2025-07-15",
// descripcion = "Presentación especial de una obra clásica con actores destacados.",
// imagen = "https://via.placeholder.com/150",
// contacto = "445566778",
// noContacto = "998877665",
// emailCont = "teatro@eventos.com",
// estado = "Activo",
// destacado = null
// ),
// Event(
// id = "8",
// idTeve = 1008,
// titulo = "Cine al Aire Libre",
// ubicacion = "Parque Cultural",
// direccion = "Boulevard de las Estrellas 20, Ciudad",
// entGratuita = "Sí",
// fInicio = "2025-08-01",
// fFinal = "2025-08-01",
// descripcion = "Disfruta de una proyección especial bajo las estrellas.",
// imagen = "https://via.placeholder.com/150",
// contacto = "112233445",
// noContacto = "556677889",
// emailCont = "cine@eventos.com",
// estado = "Activo",
// destacado = 1
// ),
// Event(
// id = "9",
// idTeve = 1009,
// titulo = "Concierto de Jazz",
// ubicacion = "Auditorio Municipal",
// direccion = "Calle Armónica 10, Ciudad",
// entGratuita = "No",
// fInicio = "2025-08-10",
// fFinal = "2025-08-10",
// descripcion = "Un concierto único con los mejores músicos de jazz.",
// imagen = "https://via.placeholder.com/150",
// contacto = "223344556",
// noContacto = "667788990",
// emailCont = "jazz@eventos.com",
// estado = "Activo",
// destacado = null
// ),
// Event(
// id = "10",
// idTeve = 1010,
// titulo = "Feria del Libro",
// ubicacion = "Plaza de la Cultura",
// direccion = "Avenida Literaria 15, Ciudad",
// entGratuita = "Sí",
// fInicio = "2025-09-05",
// fFinal = "2025-09-07",
// descripcion = "Encuentra libros únicos y participa en charlas con autores.",
// imagen = "https://via.placeholder.com/150",
// contacto = "334455667",
// noContacto = "776655443",
// emailCont = "feria@libros.com",
// estado = "Activo",
// destacado = 1
// ),
// Event(
// id = "11",
// idTeve = 1011,
// titulo = "Taller de Fotografía",
// ubicacion = "Centro Artístico",
// direccion = "Calle de las Lentes 22, Ciudad",
// entGratuita = "No",
// fInicio = "2025-09-15",
// fFinal = "2025-09-16",
// descripcion = "Aprende técnicas avanzadas de fotografía con expertos.",
// imagen = "https://via.placeholder.com/150",
// contacto = "445566778",
// noContacto = "998877665",
// emailCont = "foto@talleres.com",
// estado = "Activo",
// destacado = null
// ),
// Event(
// id = "12",
// idTeve = 1012,
// titulo = "Festival de Cine Independiente",
// ubicacion = "Teatro Principal",
// direccion = "Avenida del Cine 5, Ciudad",
// entGratuita = "No",
// fInicio = "2025-10-01",
// fFinal = "2025-10-05",
// descripcion = "Proyecciones y talleres con los mejores cineastas independientes.",
// imagen = "https://via.placeholder.com/150",
// contacto = "556677889",
// noContacto = "112233445",
// emailCont = "cineindie@eventos.com",
// estado = "Activo",
// destacado = 1
// )
// )
//
//
//
// fun fetchEvents(): List<Event> {
// return events
// }
//
//
// /* suspend fun fetchEvents(): List<Event>{
// return try {
// val apiService = RetrofitInstance.api
// apiService.getEvents()
// } catch (e: Exception) {
// emptyList<Event>()
// }
// }
// */