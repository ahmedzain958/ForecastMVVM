package com.zain.forecastmvvm.data.network.response

import com.google.gson.annotations.SerializedName
import com.zain.forecastmvvm.data.db.entity.WeatherLocation
import com.zain.forecastmvvm.data.network.response.ForecastDaysContainer

data class FutureWeatherResponse(
    @SerializedName("forecast")
    val futureWeatherEntries: ForecastDaysContainer,
    val location: WeatherLocation
)