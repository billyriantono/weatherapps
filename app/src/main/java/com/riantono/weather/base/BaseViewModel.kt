package com.riantono.weather.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel(),LifecycleObserver {

    private val compositeDisposable = CompositeDisposable()

    protected fun Disposable.collect() = compositeDisposable.add(this)

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unsubscribeViewModel() {
        compositeDisposable.dispose()
    }
}