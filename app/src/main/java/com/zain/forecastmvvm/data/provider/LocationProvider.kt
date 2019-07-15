package com.zain.forecastmvvm.data.provider

import com.zain.forecastmvvm.data.db.entity.WeatherLocation
interface LocationProvider {
    suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean
    suspend fun getPreferredLocationString(): String
}