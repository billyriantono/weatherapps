package com.riantono.weather


import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation

/**
 * Main Fragment
 *
 */
class MainFragment : Fragment() {

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


}
