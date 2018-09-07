package com.riantono.weather.repository.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlacePrediction(@field:SerializedName("predictions") val predictions: List<Place>) : Parcelable {
}