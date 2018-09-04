package com.riantono.weather.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.riantono.weather.base.BaseDao
import com.riantono.weather.data.entity.Weather
import java.util.*

interface WeatherDao : BaseDao<Weather> {
    @Insert
    override fun insert(item: Weather)

    @Query("SELECT * FROM tbl_weather WHERE id = :id")
    override fun findOneItem(id: Int): LiveData<Weather>

    @Query("SELECT * FROM tbl_weather ORDER BY id DESC")
    override fun findAll(): LiveData<List<Weather>>

    @Query("DELETE FROM tbl_weather WHERE id = :id")
    override fun delete(id: Int)

    @Query("SELECT * FROM tbl_weather WHERE location_id = :locationId")
    fun findAllWeatherByLocationId(locationId: Int): LiveData<List<Weather>>

    @Query("SELECT * FROM tbl_weather WHERE location_id = :locationId AND timestamp BETWEEN :startDate AND :endDate")
    fun findAllWeatherByLocationIdAndRangeTimestamp(locationId: Int, startDate: Long, endDate: Long): LiveData<List<Weather>>
}