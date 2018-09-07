package com.riantono.weather.ui.fragments


import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.android.gms.common.api.internal.LifecycleFragment
import com.riantono.weather.R
import com.riantono.weather.ui.lifecycle.LiveLocationManager
import timber.log.Timber


/**
 * Main Fragment
 *
 */
class MainFragment : Fragment() {

    private var gpsListener: LocationListener = MyLocationListener()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_main, container, false)

        view.findViewById<FloatingActionButton>(R.id.btn_search_city)?.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_searchCityFragment, null)
        )

        view.findViewById<Button>(R.id.btn_add_search_city)?.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_searchCityFragment, null)
        )
        // Inflate the layout for this fragment
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (ActivityCompat.checkSelfPermission(this@MainFragment.context!!,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this@MainFragment.context!!,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this@MainFragment.activity!!,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                    MainFragment.REQUEST_LOCATION_PERMISSION)
        } else {
            bindLocationListener()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0].equals(PackageManager.PERMISSION_GRANTED) && grantResults[1].equals(PackageManager.PERMISSION_GRANTED)) {
            bindLocationListener()
        } else {
            Toast.makeText(this@MainFragment.context, "We need your location, for getting the current weather", Toast.LENGTH_SHORT).show()
        }
    }

    private fun bindLocationListener() {
        LiveLocationManager().bindLocationListener(this@MainFragment, gpsListener, activity?.applicationContext!!)
    }

    companion object {
        const val REQUEST_LOCATION_PERMISSION = 1
    }

    private inner class MyLocationListener : LocationListener {
        override fun onLocationChanged(location: Location) {
            Timber.d("Current Location : " + location.latitude + "," + location.longitude);
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}

        override fun onProviderEnabled(provider: String) {
            Toast.makeText(this@MainFragment.context,
                    "Provider enabled: $provider", Toast.LENGTH_SHORT).show()
        }

        override fun onProviderDisabled(provider: String) {}
    }
}
