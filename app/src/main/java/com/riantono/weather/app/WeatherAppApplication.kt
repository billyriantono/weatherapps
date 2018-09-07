package com.riantono.weather.app

import android.app.Application
import com.riantono.weather.BuildConfig
import com.riantono.weather.dagger.component.AppComponent
import com.riantono.weather.dagger.component.DaggerAppComponent
import com.riantono.weather.dagger.module.AppModule
import com.riantono.weather.dagger.module.NetworkModule
import timber.log.Timber

class WeatherAppApplication : Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this))
                .networkModule(NetworkModule()).build()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}