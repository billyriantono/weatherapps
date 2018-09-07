package com.riantono.weather.base

abstract class BaseMapper<ResponseEntity, ModelEntity> {

    fun transform(responseEntity: ResponseEntity) : ModelEntity {
        return map(responseEntity)
    }

    protected abstract fun map(responseEntity: ResponseEntity): ModelEntity
}