package com.riantono.weather.repository.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(@field:SerializedName("dt") val dt: Long,
                   @field:SerializedName("main") val main: Main,
                   @field:SerializedName("wind") val wind: Wind,
                   @field:SerializedName("weather") val weather: List<WeatherDetail>) : Parcelable