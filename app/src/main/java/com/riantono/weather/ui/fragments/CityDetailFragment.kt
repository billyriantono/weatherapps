package com.riantono.weather.ui.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.riantono.weather.R
import com.riantono.weather.data.entity.Location


/**
 * A simple [Fragment] subclass.
 *
 */
class CityDetailFragment : Fragment() {

    private val selectedCity by lazy {
        arguments?.getParcelable<Location>(KEY_SELECTED_CITY)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_city_detail, container, false)
        
        return view
    }

    companion object {
        val KEY_SELECTED_CITY = "selected_city"
    }


}
