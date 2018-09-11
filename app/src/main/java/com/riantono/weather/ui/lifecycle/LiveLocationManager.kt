package com.riantono.weather.ui.lifecycle

import android.annotation.SuppressLint
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log

class LiveLocationManager {
    fun bindLocationListener(lifecycleOwner: LifecycleOwner, locationListener: LocationListener, context: Context) {
        LiveLocationListener(lifecycleOwner, locationListener, context)
    }

    inner class LiveLocationListener constructor(
            lifecycleOwner: LifecycleOwner,
            locationListener: LocationListener,
            context: Context
    ) : LifecycleObserver {
        private var context: Context = context
        private lateinit var locationManager: LocationManager
        private var locationListener = locationListener

        init {
            lifecycleOwner.lifecycle.addObserver(this)
        }

        @SuppressLint("MissingPermission")
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun addLocationListener() {
            locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, locationListener, null)
            Log.d("LiveLocationManager", "Listener added")

            val lastLocation = locationManager.getLastKnownLocation(
                    LocationManager.GPS_PROVIDER)
            if (lastLocation != null) {
                locationListener.onLocationChanged(lastLocation)
            }
        }


        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun removeLocationListener() {
            locationManager.removeUpdates(locationListener);
            Log.d("LiveLocationManager", "Listener removed")
        }
    }
}