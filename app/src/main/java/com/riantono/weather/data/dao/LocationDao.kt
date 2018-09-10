package com.riantono.weather.data.dao

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.riantono.weather.base.BaseDao
import com.riantono.weather.data.entity.Location

@Dao
interface LocationDao : BaseDao<Location> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert(item: Location)

    @Query("SELECT * from tbl_location where latitude = :latitude AND longitude = :longitude ORDER BY id DESC")
    fun findOneItemByLatitudeAndLongitude(latitude: Double, longitude: Double): DataSource.Factory<Int, Location>

    @Query("SELECT * FROM tbl_location ORDER BY id DESC")
    override fun findAll(): DataSource.Factory<Int, Location>

    @Query("DELETE FROM tbl_location where id = :id")
    override fun delete(id: Int)

    @Query("SELECT * FROM tbl_location WHERE id = :id ORDER BY id DESC")
    override fun findOneItem(id: Int): LiveData<Location>
}