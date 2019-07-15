package com.zain.forecastmvvm.ui.weather.current

import androidx.lifecycle.ViewModel;
import com.zain.forecastmvvm.data.provider.UnitProvider
import com.zain.forecastmvvm.data.repository.ForecastRepository
import com.zain.forecastmvvm.internal.UnitSystem
import com.zain.forecastmvvm.internal.lazyDeferred
import com.zain.forecastmvvm.ui.base.WeatherViewModel

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    private val unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository,unitProvider) {
    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(super.isMetricUnit)
    }
}
