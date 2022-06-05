package com.example.testwildberries.model

import com.google.gson.annotations.SerializedName

data class TravelResponse(
    @SerializedName("data")
    val tours: List<Travel>
)