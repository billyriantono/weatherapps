package com.riantono.weather.repository.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sys(@field:SerializedName("country") val country: String,
                @field:SerializedName("sunrise") val sunrise: Long,
                @field:SerializedName("sunset") val sunset: Long) : Parcelable