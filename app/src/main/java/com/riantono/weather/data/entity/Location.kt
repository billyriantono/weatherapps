package com.riantono.weather.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "tbl_location")
class Location {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
    @ColumnInfo(name = "latitude")
    val latitude: Double = 0.0
    @ColumnInfo(name = "longitude")
    val longitude: Double = 0.0
    @ColumnInfo(name = "address")
    val address: String = ""
    @ColumnInfo(name = "city")
    val city: String = ""
    @ColumnInfo(name = "country")
    val country: String = ""
}