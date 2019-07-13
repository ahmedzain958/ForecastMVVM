package com.zain.forecastmvvm.data.provider

import com.zain.forecastmvvm.internal.UnitSystem


interface UnitProvider {
    fun getUnitSystem(): UnitSystem
}