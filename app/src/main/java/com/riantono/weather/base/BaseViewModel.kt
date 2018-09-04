package com.riantono.weather.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    abstract fun unsubscribeViewModel()
}