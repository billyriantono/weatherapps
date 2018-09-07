package com.riantono.weather.dagger.component

import android.content.Context
import com.riantono.weather.dagger.module.AppModule
import com.riantono.weather.dagger.module.NetworkModule
import com.riantono.weather.dagger.scope.AppScope
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit


@AppScope
@Component(modules = [AppModule::class , NetworkModule::class])
interface AppComponent {
    fun retrofit(): Retrofit

    fun context(): Context

    fun okHttpClient(): OkHttpClient
}