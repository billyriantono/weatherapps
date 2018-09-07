package com.riantono.weather.ui.fragments.main


import android.Manifest
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
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
import com.riantono.weather.R
import com.riantono.weather.app.WeatherAppApplication
import com.riantono.weather.databinding.FragmentMainBinding
import com.riantono.weather.ui.fragments.main.dagger.DaggerMainComponent
import com.riantono.weather.ui.fragments.main.dagger.MainModule
import com.riantono.weather.ui.lifecycle.LiveLocationManager
import timber.log.Timber
import javax.inject.Inject


/**
 * Main Fragment
 *
 */
class MainFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    lateinit var viewModel: MainViewModel
    lateinit var binding: FragmentMainBinding

    private var gpsListener: LocationListener = MyLocationListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerMainComponent.builder()
                .appComponent((activity?.application as WeatherAppApplication).appComponent)
                .mainModule(MainModule())
                .build().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        var view = binding.root

        binding.setLifecycleOwner(this)
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
                    REQUEST_LOCATION_PERMISSION)
        } else {
            bindLocationListener()
        }

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.let { lifecycle.addObserver(it) }

        viewModel.currentCityWeatherData.observe(this, Observer { weatherData ->
            weatherData.run {
                Timber.d("Perubahan Cuaca : " + weatherData?.temp)
            }
        })

        binding.viewModel = viewModel

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
            Timber.d("Current Location : " + location.latitude + "," + location.longitude)
            viewModel.getCurrentWeather(location.latitude, location.longitude)
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}

        override fun onProviderEnabled(provider: String) {
            Toast.makeText(this@MainFragment.context,
                    "Provider enabled: $provider", Toast.LENGTH_SHORT).show()
        }

        override fun onProviderDisabled(provider: String) {}
    }
}
