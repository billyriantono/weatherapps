package com.riantono.weather.data.mapper

import com.riantono.weather.base.BaseMapper
import com.riantono.weather.repository.entity.Weather
import com.riantono.weather.ui.entity.ForecastModel
import javax.inject.Inject

class ListForecastMapper @Inject constructor() : BaseMapper<List<Weather>, List<ForecastModel>>() {
    override fun map(responseEntity: List<Weather>): List<ForecastModel> {
        var listOfForecast = ArrayList<ForecastModel>()
        var forecastMapper = ForecastMapper()
        responseEntity.forEach { listOfForecast.add(forecastMapper.transform(it)) }
        return listOfForecast
    }
}