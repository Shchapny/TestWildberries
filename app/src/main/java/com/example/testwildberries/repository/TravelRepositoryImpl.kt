package com.example.testwildberries.repository

import com.example.testwildberries.api.TravelApi
import com.example.testwildberries.model.Travel
import javax.inject.Inject

class TravelRepositoryImpl @Inject constructor(private val api: TravelApi) : TravelRepository {

    override suspend fun getFromApi(): List<Travel> {
        return api.getTours().tours
    }
}