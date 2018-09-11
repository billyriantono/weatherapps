package com.riantono.weather.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "tbl_location")
data class Location(@PrimaryKey(autoGenerate = true)
                    val id: Int?,
                    @ColumnInfo(name = "latitude")
                    val latitude: Double,
                    @ColumnInfo(name = "longitude")
                    val longitude: Double,
                    @ColumnInfo(name = "address")
                    val address: String) : Parcelable
