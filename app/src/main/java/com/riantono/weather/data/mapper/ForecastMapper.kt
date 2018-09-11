package com.riantono.weather.data.mapper

import com.riantono.weather.base.BaseMapper
import com.riantono.weather.repository.entity.Weather
import com.riantono.weather.ui.entity.ForecastModel
import javax.inject.Inject

class ForecastMapper @Inject constructor() : BaseMapper<Weather, ForecastModel>() {

    override fun map(responseEntity: Weather): ForecastModel {
        return ForecastModel(responseEntity.main.temp, responseEntity.main.humidity,
                responseEntity.wind.speed, responseEntity.weather[0].main, responseEntity.dt)
    }

}