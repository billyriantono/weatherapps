package com.riantono.weather.base

import android.arch.persistence.room.Dao

@Dao
interface BaseDao<T> {
    fun insert(item : T)
    fun findAll() : List<T>
    fun delete(id : Long)
    fun findOneItem(id : Long)
}