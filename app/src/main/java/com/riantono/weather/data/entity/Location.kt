package com.riantono.weather.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "tbl_location")
data class Location(@PrimaryKey(autoGenerate = true)
               val id: Int,
               @ColumnInfo(name = "latitude")
               val latitude: Double,
               @ColumnInfo(name = "longitude")
               val longitude: Double,
               @ColumnInfo(name = "address")
               val address: String,
               @ColumnInfo(name = "city")
               val city: String,
               @ColumnInfo(name = "country")
               val country: String)
