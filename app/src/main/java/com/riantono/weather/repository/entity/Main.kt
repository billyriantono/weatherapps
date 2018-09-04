package com.riantono.weather.repository.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Main(@field:SerializedName("temp") val temp: Double, @field:SerializedName("pressure") val pressure: Int, @field:SerializedName("humidity") val humidity: Int) : Parcelable {

}