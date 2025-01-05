package com.example.actividad1_eventsapp.model

class EventProvider {
    val events = listOf(
        Event(
            id = "1",
            idTeve = 1001,
            titulo = "Festival de Música",
            ubicacion = "Parque Central",
            direccion = "Calle 123, Ciudad",
            entGratuita = "Sí",
            fInicio = "2025-01-10",
            fFinal = "2025-01-12",
            descripcion = "Un festival de música con bandas locales e internacionales.",
            imagen = "https://example.com/festival.jpg",
            contacto = "123456789",
            noContacto = "987654321",
            emailCont = "contacto@festival.com",
            estado = "Activo",
            destacado = 1
        ),
        Event(
            id = "2",
            idTeve = 1002,
            titulo = "Exposición de Arte",
            ubicacion = "Museo Nacional",
            direccion = "Avenida de las Artes, Ciudad",
            entGratuita = "No",
            fInicio = "2025-02-01",
            fFinal = "2025-02-28",
            descripcion = "Exposición de arte contemporáneo con artistas nacionales.",
            imagen = "https://example.com/arte.jpg",
            contacto = "112233445",
            noContacto = "998877665",
            emailCont = "info@museo.com",
            estado = "Activo",
            destacado = null
        ),
        Event(
            id = "3",
            idTeve = 1003,
            titulo = "Torneo de Ajedrez",
            ubicacion = "Club Deportivo",
            direccion = "Calle Estratégica 5, Ciudad",
            entGratuita = "Sí",
            fInicio = "2025-03-15",
            fFinal = "2025-03-16",
            descripcion = "Torneo para jugadores de todos los niveles. Inscripción gratuita.",
            imagen = "https://example.com/ajedrez.jpg",
            contacto = "334455667",
            noContacto = "776655443",
            emailCont = "info@clubdeportivo.com",
            estado = "Activo",
            destacado = 1
        ),
        Event(
            id = "4",
            idTeve = 1004,
            titulo = "Feria del Libro",
            ubicacion = "Centro de Convenciones",
            direccion = "Av. Cultura 45, Ciudad",
            entGratuita = "No",
            fInicio = "2025-04-20",
            fFinal = "2025-04-25",
            descripcion = "Feria con escritores y editoriales reconocidas.",
            imagen = "https://example.com/libro.jpg",
            contacto = "998877665",
            noContacto = "334455667",
            emailCont = "info@ferialibro.com",
            estado = "Activo",
            destacado = null
        ),
        Event(
            id = "5",
            idTeve = 1005,
            titulo = "Carrera Solidaria",
            ubicacion = "Plaza Mayor",
            direccion = "Calle Principal 1, Ciudad",
            entGratuita = "Sí",
            fInicio = "2025-05-01",
            fFinal = "2025-05-01",
            descripcion = "Carrera de 10 km a beneficio de organizaciones locales.",
            imagen = "https://example.com/carrera.jpg",
            contacto = "556677889",
            noContacto = "889977665",
            emailCont = "info@carrerasolidaria.com",
            estado = "Activo",
            destacado = 1
        )
    )



    fun fetchEvents(): List<Event> {
        return events
    }
}
