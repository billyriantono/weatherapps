package com.riantono.weather.ui.fragments.main

import android.arch.lifecycle.MutableLiveData
import com.riantono.weather.base.BaseViewModel
import com.riantono.weather.data.mapper.WeatherMapper
import com.riantono.weather.repository.WeatherRepository
import com.riantono.weather.repository.entity.Weather
import com.riantono.weather.ui.entity.WeatherModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import timber.log.Timber


class MainViewModel @Inject constructor(weatherRepository: WeatherRepository, weatherMapper: WeatherMapper) : BaseViewModel() {
    var weatherRepository = weatherRepository
    var weatherMapper = weatherMapper
    val currentCityWeatherData = MutableLiveData<WeatherModel>()

    fun getCurrentWeather(latitude: Double, longitude: Double) {
        weatherRepository.getWeatherOnSpecificLocation(latitude, longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).flatMap({ weatherResponse -> Observable.just<Weather>(weatherResponse) })
                .subscribe({ weatherResponse ->
                    Timber.d("Current Weather :  " + weatherResponse.main.temp)
                    currentCityWeatherData.postValue(weatherMapper.transform(weatherResponse))
                }, { throwable ->
                    Timber.e(throwable, "Login failed: $throwable")

                }).collect()

    }
}