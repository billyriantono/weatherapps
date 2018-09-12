package com.riantono.weather.ui.fragments.citydetail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.riantono.weather.data.mapper.ListForecastMapper
import com.riantono.weather.repository.WeatherRepository
import javax.inject.Inject

class CityDetailViewModelFactory @Inject constructor(weatherApiRepository: WeatherRepository, listOfForecastMapper: ListForecastMapper) : ViewModelProvider.Factory {
    private val weatherApiRepository = weatherApiRepository
    private val listOfForecastMapper = listOfForecastMapper

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.cast(CityDetailViewModel(weatherApiRepository, listOfForecastMapper))
    }
}