package com.riantono.weather.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "tbl_location")
class Location {
    @PrimaryKey(autoGenerate = true)
    private val id: Int = 0
    @ColumnInfo(name = "latitude")
    private val latitude: Double = 0.0
    @ColumnInfo(name = "longitude")
    private val longitude: Double = 0.0
    @ColumnInfo(name = "address")
    private val address: String = ""


    fun getLatitude(): Double {
        return this.latitude
    }

}
