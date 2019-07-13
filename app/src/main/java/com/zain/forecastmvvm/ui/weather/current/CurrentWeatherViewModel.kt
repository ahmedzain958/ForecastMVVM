package com.zain.forecastmvvm.ui.weather.current

import androidx.lifecycle.ViewModel;
import com.zain.forecastmvvm.data.provider.UnitProvider
import com.zain.forecastmvvm.data.repository.ForecastRepository
import com.zain.forecastmvvm.internal.UnitSystem
import com.zain.forecastmvvm.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    private val unitProvider: UnitProvider
) : ViewModel() {
    private val unitSystem = UnitSystem.METRIC

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        // lazy in order not to be called whenever the class instantiated
        forecastRepository.getCurrentWeather(isMetric)
    }
}
