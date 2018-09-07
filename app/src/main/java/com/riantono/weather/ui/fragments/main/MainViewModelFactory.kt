package com.riantono.weather.ui.fragments.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.riantono.weather.data.mapper.WeatherMapper
import com.riantono.weather.repository.WeatherRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(weatherApiRepository: WeatherRepository, weatherMapper: WeatherMapper) : ViewModelProvider.Factory {
    private var weatherApiRepository = weatherApiRepository
    private var weatherMapper = weatherMapper

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.cast(MainViewModel(weatherApiRepository, weatherMapper))
    }
}