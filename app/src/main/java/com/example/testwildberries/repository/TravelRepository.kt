package com.example.testwildberries.repository

import com.example.testwildberries.model.Travel

interface TravelRepository {

    suspend fun getFromApi(): List<Travel>
}