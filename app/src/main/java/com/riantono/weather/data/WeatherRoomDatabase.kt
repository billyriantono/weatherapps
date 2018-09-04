package com.riantono.weather.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.riantono.weather.BuildConfig
import com.riantono.weather.data.dao.LocationDao
import com.riantono.weather.data.dao.WeatherDao
import com.riantono.weather.data.entity.Location
import com.riantono.weather.data.entity.Weather

@Database(
        entities = [
            Location::class,
            Weather::class
        ],
        version = BuildConfig.ROOM_DATABASE_VERSION,
        exportSchema = false
)
abstract class WeatherRoomDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
    abstract fun weatherDao(): WeatherDao
}