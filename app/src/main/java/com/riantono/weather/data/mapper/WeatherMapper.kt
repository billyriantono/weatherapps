package com.riantono.weather.data.mapper

import com.riantono.weather.base.BaseMapper
import com.riantono.weather.repository.entity.Weather
import com.riantono.weather.ui.entity.WeatherModel
import javax.inject.Inject

class WeatherMapper @Inject constructor() : BaseMapper<Weather, WeatherModel>() {

    override fun map(responseEntity: Weather): WeatherModel {
        return WeatherModel(responseEntity.main.temp, responseEntity.main.humidity,
                responseEntity.wind.speed, responseEntity.weather[0].main)
    }

}