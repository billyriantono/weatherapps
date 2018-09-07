package com.riantono.weather.dagger.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.riantono.weather.BuildConfig
import com.riantono.weather.dagger.scope.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit


@Module
class NetworkModule {
    @AppScope
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.OPEN_WEATHER_BASE_API)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()
    }

    @AppScope
    @Provides
    fun provideGson(): Gson {
        var gsonBuilder: GsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @AppScope
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).cache(cache)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
    }

    @AppScope
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { message -> Timber.tag("Weather App").d(message) }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @AppScope
    @Provides
    fun proviceCache(directory: File): Cache {
        return Cache(directory, (10 * 1000 * 1000).toLong()) // 10MB cache
    }

    @AppScope
    @Provides
    fun provideCacheFile(context: Context): File {
        val file = File(context.getCacheDir(), "weather_apps_cache")
        file.mkdirs()
        return file
    }


}