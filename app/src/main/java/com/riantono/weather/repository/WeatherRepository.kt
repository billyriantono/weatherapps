package com.riantono.weather.repository

import com.riantono.weather.repository.entity.Weather
import com.riantono.weather.repository.services.WeatherApiService
import io.reactivex.Observable

class WeatherRepository(weatherApiService: WeatherApiService) {
    private var weatherApiService = weatherApiService
    fun getWeatherOnSpecificLocation(latitude: Double, longitude: Double): Observable<Weather> {
        return weatherApiService.getWeatherOnCurrentLocation(latitude, longitude,"3087c9d59588631230e7e1e073dd541e")
    }
}