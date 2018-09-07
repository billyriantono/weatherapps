package com.riantono.weather.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.riantono.weather.BuildConfig
import com.riantono.weather.data.dao.LocationDao
import com.riantono.weather.data.entity.Location

@Database(
        entities = [
            Location::class
        ],
        version = BuildConfig.ROOM_DATABASE_VERSION,
        exportSchema = false
)
abstract class WeatherRoomDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
}