package com.zain.forecastmvvm.ui.base

import androidx.lifecycle.ViewModel
import com.zain.forecastmvvm.data.provider.UnitProvider
import com.zain.forecastmvvm.data.repository.ForecastRepository
import com.zain.forecastmvvm.internal.UnitSystem
import com.zain.forecastmvvm.internal.lazyDeferred


abstract class WeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : ViewModel() {

    private val unitSystem = unitProvider.getUnitSystem()

    val isMetricUnit: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weatherLocation by lazyDeferred {
//        forecastRepository.getWeatherLocation()
    }
}