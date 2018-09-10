package com.riantono.weather.ui.fragments.main


import android.Manifest
import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
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
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.places.AutocompleteFilter
import com.google.android.gms.location.places.ui.PlaceAutocomplete
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

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerMainComponent.builder()
                .appComponent((activity?.application as WeatherAppApplication).appComponent)
                .mainModule(MainModule(this@MainFragment.activity!!))
                .build().inject(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this@MainFragment.context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        var view = binding.root

        binding.setLifecycleOwner(this)
        view.findViewById<FloatingActionButton>(R.id.btn_search_city)?.setOnClickListener { showAutocompletePlaces() }

        view.findViewById<Button>(R.id.btn_add_search_city)?.setOnClickListener { showAutocompletePlaces() }
        // Inflate the layout for this fragment
        return view
    }

    private fun showAutocompletePlaces() {
        val typeFilter = AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                .build()

        val intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                .setFilter(typeFilter)
                .build(this@MainFragment.activity)
        startActivityForResult(intent, REQUEST_AUTO_COMPLETE_PLACE)
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
            getCurrentLocation()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode.equals(REQUEST_AUTO_COMPLETE_PLACE)) {
            viewModel.onHandlePlaceAutoComplete(resultCode, data)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0].equals(PackageManager.PERMISSION_GRANTED) && grantResults[1].equals(PackageManager.PERMISSION_GRANTED)) {
            getCurrentLocation()
        } else {
            Toast.makeText(this@MainFragment.context, "We need your location, for getting the current weather", Toast.LENGTH_SHORT).show()
        }
    }


    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            run {
                if (location != null) {
                    viewModel.getCurrentWeather(location.latitude, location.longitude)
                }
            }
        }
    }

    companion object {
        const val REQUEST_LOCATION_PERMISSION = 1
        const val REQUEST_AUTO_COMPLETE_PLACE = 2
    }
}
