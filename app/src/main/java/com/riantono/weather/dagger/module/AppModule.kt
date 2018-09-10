package com.riantono.weather.dagger.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.riantono.weather.dagger.scope.AppScope
import com.riantono.weather.data.WeatherRoomDatabase
import com.riantono.weather.data.dao.LocationDao
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class AppModule @Inject public constructor(app: Application) {
    var context: Context = app.applicationContext

    @AppScope
    @Provides
    fun provideContext(): Context {
        return context
    }


    @AppScope
    @Provides
    fun provideDb(context: Context): WeatherRoomDatabase {
        return Room.databaseBuilder(context, WeatherRoomDatabase::class.java, "weather_apps.db")
                .fallbackToDestructiveMigration().build()
    }

    @AppScope
    @Provides
    fun provideLocationDao(db: WeatherRoomDatabase): LocationDao {
        return db.locationDao()
    }
}