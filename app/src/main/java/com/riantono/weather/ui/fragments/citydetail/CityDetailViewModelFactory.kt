package com.riantono.weather.ui.fragments.citydetail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.riantono.weather.data.mapper.ListForecastMapper
import com.riantono.weather.repository.WeatherRepository
import javax.inject.Inject

class CityDetailViewModelFactory @Inject constructor(context: Context, weatherApiRepository: WeatherRepository, listOfForecastMapper: ListForecastMapper) : ViewModelProvider.Factory {
    private val weatherApiRepository = weatherApiRepository
    private val listOfForecastMapper = listOfForecastMapper
    private val context = context

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.cast(CityDetailViewModel(context, weatherApiRepository, listOfForecastMapper))
    }
}