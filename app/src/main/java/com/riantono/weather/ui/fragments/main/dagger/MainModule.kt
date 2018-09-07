package com.riantono.weather.ui.fragments.main.dagger

import com.riantono.weather.data.mapper.WeatherMapper
import com.riantono.weather.repository.WeatherRepository
import com.riantono.weather.repository.services.WeatherApiService
import com.riantono.weather.ui.fragments.main.MainViewModelFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Inject

@Module
class MainModule @Inject constructor() {

    @MainScope
    @Provides
    fun provideViewModelFactory(weatherRepository: WeatherRepository, weatherMapper: WeatherMapper): MainViewModelFactory {
        return MainViewModelFactory(weatherRepository, weatherMapper)
    }

    @MainScope
    @Provides
    fun provideWeatherMapper(): WeatherMapper {
        return WeatherMapper()
    }

    @MainScope
    @Provides
    fun provideWeatherRepository(weatherApiService: WeatherApiService): WeatherRepository {
        return WeatherRepository(weatherApiService)
    }

    @MainScope
    @Provides
    fun provideWeatherApiService(retrofit: Retrofit): WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }
}