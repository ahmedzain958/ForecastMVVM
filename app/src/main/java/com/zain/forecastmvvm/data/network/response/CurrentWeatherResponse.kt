package com.zain.forecastmvvm.data.network.response

import com.google.gson.annotations.SerializedName
import com.zain.forecastmvvm.data.db.entity.CurrentWeatherEntry
import com.zain.forecastmvvm.data.db.entity.Location


data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: Location
)