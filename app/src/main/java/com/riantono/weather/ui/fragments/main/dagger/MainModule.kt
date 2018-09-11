package com.riantono.weather.ui.fragments.main.dagger

import android.content.Context
import com.riantono.weather.data.WeatherRoomDatabase
import com.riantono.weather.data.dao.LocationDao
import com.riantono.weather.data.mapper.WeatherMapper
import com.riantono.weather.repository.LocationRepository
import com.riantono.weather.repository.WeatherRepository
import com.riantono.weather.repository.services.WeatherApiService
import com.riantono.weather.ui.fragments.main.MainViewModelFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Inject

@Module
class MainModule @Inject constructor(context: Context) {
    private val context = context

    @MainScope
    @Provides
    fun provideViewModelFactory(weatherRepository: WeatherRepository, locationRepository: LocationRepository, weatherMapper: WeatherMapper): MainViewModelFactory {
        return MainViewModelFactory(context, weatherRepository, locationRepository, weatherMapper)
    }

    @MainScope
    @Provides
    fun provideLocationRepository(locationDao: LocationDao): LocationRepository {
        return LocationRepository(locationDao)
    }

    @MainScope
    @Provides
    fun provideLocationDao(roomDatabase: WeatherRoomDatabase): LocationDao {
        return roomDatabase.locationDao()
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