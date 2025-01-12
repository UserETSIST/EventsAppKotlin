
package com.example.actividad1_eventsapp.retrofit


import com.example.actividad1_eventsapp.model.ApiResponse
import com.example.actividad1_eventsapp.model.Event
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers("Authorization: 3d524a53c110e4c22463b10ed32cef9d")
    @GET("go2event/v1/list/even?page=1&limit=100")

    suspend fun getEvents(): Response<ApiResponse>
}


