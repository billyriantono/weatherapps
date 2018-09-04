package com.riantono.weather.app

import android.app.Application
import com.riantono.weather.BuildConfig
import timber.log.Timber

class WeatherAppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}