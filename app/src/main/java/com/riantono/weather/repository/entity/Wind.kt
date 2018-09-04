package com.riantono.weather.repository.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wind(@field:SerializedName("speed") val speed: Double, @field:SerializedName("deg") val deg: Double) : Parcelable