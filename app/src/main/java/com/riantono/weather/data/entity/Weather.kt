package com.riantono.weather.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "tbl_weather",
        foreignKeys = [(ForeignKey(entity = Location::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("location_id"), onDelete = CASCADE, onUpdate = CASCADE))])
class Weather {
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
    @ColumnInfo(name = "location_id")
    val locationId: Int = 0
    @ColumnInfo(name = "timestamp")
    val timestamp: Long = 0
    @ColumnInfo(name = "weather")
    val weather: String = ""
    @ColumnInfo(name = "temp")
    val temp: Double = 0.0
    @ColumnInfo(name = "pressure")
    val pressure: Double = 0.0
    @ColumnInfo(name = "humidity")
    val humidity: Double = 0.0
}