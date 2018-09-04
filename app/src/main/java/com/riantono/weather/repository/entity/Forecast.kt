package com.riantono.weather.repository.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Forecast(@field:SerializedName("cnt") val cnt: Int, @field:SerializedName("list") val list: List<Weather>) : Parcelable {
}