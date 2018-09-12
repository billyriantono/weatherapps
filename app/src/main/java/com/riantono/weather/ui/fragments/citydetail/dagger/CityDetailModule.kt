package com.riantono.weather.ui.fragments.citydetail.dagger

import android.content.Context
import com.riantono.weather.data.WeatherRoomDatabase
import com.riantono.weather.data.dao.LocationDao
import com.riantono.weather.data.mapper.ListForecastMapper
import com.riantono.weather.data.mapper.WeatherMapper
import com.riantono.weather.repository.LocationRepository
import com.riantono.weather.repository.WeatherRepository
import com.riantono.weather.repository.services.WeatherApiService
import com.riantono.weather.ui.fragments.citydetail.CityDetailViewModelFactory
import com.riantono.weather.ui.fragments.main.MainViewModelFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Inject

@Module
class CityDetailModule @Inject constructor() {

    @CityDetailScope
    @Provides
    fun provideViewModelFactory(weatherRepository: WeatherRepository, listForecastMapper: ListForecastMapper): CityDetailViewModelFactory {
        return CityDetailViewModelFactory(weatherRepository, listForecastMapper)
    }

    @CityDetailScope
    @Provides
    fun provideListOfForecastMapper(): ListForecastMapper {
        return ListForecastMapper()
    }

    @CityDetailScope
    @Provides
    fun provideWeatherRepository(weatherApiService: WeatherApiService): WeatherRepository {
        return WeatherRepository(weatherApiService)
    }

    @CityDetailScope
    @Provides
    fun provideWeatherApiService(retrofit: Retrofit): WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }
}