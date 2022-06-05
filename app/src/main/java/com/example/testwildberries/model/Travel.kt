package com.example.testwildberries.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Travel(
    val startCity: String,
    val startCityCode: String,
    val endCity: String,
    val endCityCode: String,
    val startDate: String,
    val endDate: String,
    val price: Int,
    val searchToken: String,
    var liked: Boolean = false,
) : Parcelable