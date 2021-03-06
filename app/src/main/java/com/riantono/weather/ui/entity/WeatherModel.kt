package com.riantono.weather.ui.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherModel(val temp: Double, val humidity: Double, val windSpeed: Double, val description: String?) : Parcelable