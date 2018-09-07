package com.riantono.weather.repository.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherDetail(@field:SerializedName("main") val main: String,
                         @field:SerializedName("description") var description: String) : Parcelable