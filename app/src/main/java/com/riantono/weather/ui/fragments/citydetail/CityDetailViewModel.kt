package com.riantono.weather.ui.fragments.citydetail

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.riantono.weather.base.BaseViewModel
import com.riantono.weather.data.mapper.ListForecastMapper
import com.riantono.weather.repository.WeatherRepository
import com.riantono.weather.repository.entity.Forecast
import com.riantono.weather.ui.entity.ForecastModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class CityDetailViewModel @Inject constructor(weatherRepository: WeatherRepository, listOfForecastMapper: ListForecastMapper) : BaseViewModel() {
    val weatherRepository = weatherRepository
    val listOfForecastMapper = listOfForecastMapper
    val forecastList = MutableLiveData<List<ForecastModel>>()

    fun getForecastOfCity(latitude: Double, longitude: Double) {
        weatherRepository.getForecastOnSpecificLocation(latitude, longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).flatMap { weatherResponse -> Single.just<Forecast>(weatherResponse) }
                .subscribe({ weatherResponse ->
                    forecastList.postValue(listOfForecastMapper.transform(weatherResponse.list))
                }, { throwable ->
                    Timber.e(throwable, "Get Weather failed: $throwable")
                }).collect()
    }


}