package com.riantono.weather.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.riantono.weather.base.BaseDao
import com.riantono.weather.data.entity.Location

interface LocationDao : BaseDao<Location> {
    @Insert
    override fun insert(item: Location)

    @Query("SELECT * from tbl_location where latitude = :latitude AND longitude = :longitude ORDER BY id DESC")
    fun findOneItemByLatitudeAndLongitude(latitude: Double, longitude: Double): Location

    @Query("SELECT * FROM tbl_location ORDER BY id DESC")
    override fun findAll(): LiveData<List<Location>>

    @Query("DELETE FROM tbl_location where id = :id")
    override fun delete(id: Long)

    @Query("SELECT * FROM tbl_location WHERE id = :id ORDER BY id DESC")
    override fun findOneItem(id: Long): LiveData<Location>
}