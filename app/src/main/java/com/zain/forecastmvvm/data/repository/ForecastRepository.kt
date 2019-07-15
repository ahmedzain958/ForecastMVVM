package com.zain.forecastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.zain.forecastmvvm.data.db.entity.WeatherLocation
import com.zain.forecastmvvm.data.db.unitlocalized.current.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
    suspend fun getWeatherLocation(): LiveData<WeatherLocation>
}
