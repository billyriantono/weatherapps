package com.riantono.weather.ui.fragments.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.riantono.weather.data.mapper.WeatherMapper
import com.riantono.weather.repository.LocationRepository
import com.riantono.weather.repository.WeatherRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(context: Context, weatherApiRepository: WeatherRepository, locationRepository: LocationRepository, weatherMapper: WeatherMapper) : ViewModelProvider.Factory {
    private val weatherApiRepository = weatherApiRepository
    private val weatherMapper = weatherMapper
    private val context = context
    private val locationRepository = locationRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.cast(MainViewModel(context, weatherApiRepository, locationRepository, weatherMapper))
    }
}