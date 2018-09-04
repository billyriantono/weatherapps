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
data class Weather(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        @ColumnInfo(name = "location_id")
        val locationId: Int,
        @ColumnInfo(name = "timestamp")
        val timestamp: Long,
        @ColumnInfo(name = "weather")
        val weather: String,
        @ColumnInfo(name = "temp")
        val temp: Double,
        @ColumnInfo(name = "pressure")
        val pressure: Double,
        @ColumnInfo(name = "humidity")
        val humidity: Double
)