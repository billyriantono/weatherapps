package com.riantono.weather.ui.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ForecastModel(val temp: Double, val humidity: Double, val windSpeed: Double, val description: String?, val dt: Long?) : Parcelable