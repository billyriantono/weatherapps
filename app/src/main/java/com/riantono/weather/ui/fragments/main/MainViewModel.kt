package com.riantono.weather.ui.fragments.main

import android.app.Activity.RESULT_OK
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.Intent
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceAutocomplete
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


class MainViewModel @Inject constructor(context: Context, weatherRepository: WeatherRepository, weatherMapper: WeatherMapper) : BaseViewModel() {
    val weatherRepository = weatherRepository
    val weatherMapper = weatherMapper
    val currentCityWeatherData = MutableLiveData<WeatherModel>()
    val context = context

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

    fun onHandlePlaceAutoComplete(resultCode: Int, data: Intent?) {
        if (resultCode.equals(RESULT_OK)) {
            val place: Place = PlaceAutocomplete.getPlace(context, data)
            getCurrentWeather(place.latLng.latitude, place.latLng.longitude)
        } else if (resultCode.equals(PlaceAutocomplete.RESULT_ERROR)) {

        } else {

        }
    }
}