package com.riantono.weather.repository.services

import com.riantono.weather.repository.entity.Forecast
import com.riantono.weather.repository.entity.Weather
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("weather")
    fun getWeatherOnCurrentLocation(@Query("lat") latitude: Double, @Query("lon") longitude: Double, @Query("appid") appKey: String): Single<Weather>

    @GET("forecast")
    fun getForecaseOnSpecificLocation(@Query("lat") latitude: Double, @Query("lon") longitude: Double, @Query("appid") appKey: String): Single<Forecast>

}