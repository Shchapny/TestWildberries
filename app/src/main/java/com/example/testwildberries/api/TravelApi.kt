package com.example.testwildberries.api

import com.example.testwildberries.model.TravelResponse
import retrofit2.http.GET

interface TravelApi {

    @GET("cheap")
    suspend fun getTours(): TravelResponse
}

