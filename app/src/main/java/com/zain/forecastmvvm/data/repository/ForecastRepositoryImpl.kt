package com.zain.forecastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.zain.forecastmvvm.data.db.CurrentWeatherDao
import com.zain.forecastmvvm.data.db.WeatherLocationDao
import com.zain.forecastmvvm.data.db.entity.WeatherLocation
import com.zain.forecastmvvm.data.db.unitlocalized.current.UnitSpecificCurrentWeatherEntry
import com.zain.forecastmvvm.data.network.WeatherNetworkDataSource
import com.zain.forecastmvvm.data.network.response.CurrentWeatherResponse
import com.zain.forecastmvvm.data.provider.LocationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherLocationDao: WeatherLocationDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource,
    private val locationProvider: LocationProvider
) : ForecastRepository {

    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever { fetchedWeather ->
            presistFetchedCurrentWeather(fetchedWeather)
        }
    }

    private fun presistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
            weatherLocationDao.upsert(fetchedWeather.location)
        }
    }

    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry> {
        return withContext(Dispatchers.IO) {
            initWeatherData()
            return@withContext if (metric) currentWeatherDao.getWeatherMetric() else currentWeatherDao.getWeatherImperial()
        }
    }

    private suspend fun initWeatherData() {
        val lastWeatherLocation = weatherLocationDao.getLocation().value
        if (lastWeatherLocation == null || locationProvider.hasLocationChanged(lastWeatherLocation)) {
            fetchCurrentWeather()
            return
        }

        if (isFetchCurrentNeeded(lastWeatherLocation.zonedDateTime)) {
            fetchCurrentWeather()
        }
    }


    private suspend fun fetchCurrentWeather() {
        weatherNetworkDataSource.fetchCurrentWeather(
            locationProvider.getPreferredLocationString(),
            Locale.getDefault().language
        )
    }

    private fun isFetchCurrentNeeded(
        lastFetchTime: ZonedDateTime
    ): Boolean {//compatible with all versions
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }

    override suspend fun getWeatherLocation(): LiveData<WeatherLocation> {
        return withContext(Dispatchers.IO) {
            return@withContext weatherLocationDao.getLocation()
        }
    }

}