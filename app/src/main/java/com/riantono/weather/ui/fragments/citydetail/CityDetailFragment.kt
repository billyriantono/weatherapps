package com.riantono.weather.ui.fragments.citydetail


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.riantono.weather.R
import com.riantono.weather.app.WeatherAppApplication
import com.riantono.weather.data.entity.Location
import com.riantono.weather.databinding.FragmentCityDetailBinding
import com.riantono.weather.ui.entity.ForecastModel
import com.riantono.weather.ui.fragments.citydetail.adapters.ForecastListAdapter
import com.riantono.weather.ui.fragments.citydetail.dagger.CityDetailModule
import com.riantono.weather.ui.fragments.citydetail.dagger.DaggerCityDetailComponent
import com.riantono.weather.ui.fragments.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_city_detail.*
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 *
 */
class CityDetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: CityDetailViewModelFactory

    lateinit var binding: FragmentCityDetailBinding

    lateinit var viewModel: CityDetailViewModel

    private val selectedCity by lazy {
        arguments?.getParcelable<Location>(KEY_SELECTED_CITY)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerCityDetailComponent.builder().appComponent((activity?.application as WeatherAppApplication).appComponent)
                .cityDetailModule(CityDetailModule())
                .build().inject(this@CityDetailFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_city_detail, container, false)

        var view = binding.root

        binding.setLifecycleOwner(this)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CityDetailViewModel::class.java)
        viewModel.let { lifecycle.addObserver(it) }

        val adapter = ForecastListAdapter(this@CityDetailFragment.context!!)
        rv_list_forecast?.adapter = adapter
        rv_list_forecast?.layoutManager = LinearLayoutManager(this@CityDetailFragment.context, LinearLayoutManager.VERTICAL, false)

        viewModel.forecastList.observe(this, Observer<List<ForecastModel>> {
            showEmptyList(it?.size == 0)
            adapter.setList(it)
        })

        viewModel.getForecastOfCity(selectedCity?.latitude!!, selectedCity?.longitude!!)
        binding.viewModel = viewModel
    }

    private fun showEmptyList(isEmptyList: Boolean) {
        if (isEmptyList) {
            rl_empty_forecast_view?.visibility = View.VISIBLE
            rv_list_forecast?.visibility = View.GONE
        } else {
            rl_empty_forecast_view?.visibility = View.GONE
            rv_list_forecast?.visibility = View.VISIBLE
        }
    }

    companion object {
        val KEY_SELECTED_CITY = "selected_city"
    }


}
