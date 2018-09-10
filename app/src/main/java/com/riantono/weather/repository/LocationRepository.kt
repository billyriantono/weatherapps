package com.riantono.weather.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.riantono.weather.data.dao.LocationDao
import com.riantono.weather.data.entity.Location

class LocationRepository(locationDao: LocationDao) {
    private val locationDao = locationDao
    fun insertItem(item: Location) {
        locationDao.insert(item)
    }

    fun getSavedLocation(): LiveData<PagedList<Location>> {
        return LivePagedListBuilder(locationDao.findAll(), PAGE_SIZE).build()
    }


    companion object {
        val PAGE_SIZE = 10
    }
}