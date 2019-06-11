package com.zain.forecastmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zain.forecastmvvm.data.db.entity.CurrentWeatherEntry
import com.zain.forecastmvvm.data.db.unitlocalized.ImperialCurrentWeatherEntry
import com.zain.forecastmvvm.data.db.unitlocalized.MetricCurrentWeatherEntry


@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry)

    @Query("select * from current_weather where id = 0")
    fun getWeatherMetric(): LiveData<MetricCurrentWeatherEntry>

    @Query("select * from current_weather where id = 0")
    fun getWeatherImperial(): LiveData<ImperialCurrentWeatherEntry>
}