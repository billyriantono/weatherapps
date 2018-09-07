package com.riantono.weather.repository.services

import android.arch.lifecycle.LiveData
import com.riantono.weather.repository.entity.PlacePrediction
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlacesApiService {
    @GET("autocomplete/{outputType}")
    fun getPredictionAutoComplete(@Path("outputType") outputType: String,
                                  @Query("input") keyword: String,
                                  @Query("key") apiKey: String,
                                  @Query("types") types: String): Observable<PlacePrediction>
}