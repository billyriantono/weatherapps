package com.riantono.weather.base

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.Dao

@Dao
interface BaseDao<T> {
    fun insert(item: T)
    fun findAll(): DataSource.Factory<Int, T>
    fun delete(id: Int)
    fun findOneItem(id: Int): LiveData<T>
}