package com.riantono.weather.repository.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Place(@field:SerializedName("description") val description: String,
                 @field:SerializedName("place_id") val placeId: String,
                 @field:SerializedName("id") val id: String) : Parcelable {
}