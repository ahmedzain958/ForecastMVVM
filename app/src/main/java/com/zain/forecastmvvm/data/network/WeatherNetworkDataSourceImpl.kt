package com.zain.forecastmvvm.data.network

import androidx.lifecycle.*
import com.zain.forecastmvvm.data.network.response.CurrentWeatherResponse

class WeatherNetworkDataSourceImpl(
    private val apixuWeatherApiService: ApixuWeatherApiService
) : WeatherNetworkDataSource,LifecycleOwner{

    val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String, languageCode: String) {
        try {
            val fetcheCurrentWeather = apixuWeatherApiService.getCurrentWeather(
                location,
                languageCode
            )
                .await()
            _downloadedCurrentWeather.postValue()
        } catch () {

        }
    }
}