package com.riantono.weather.ui.fragments.citydetail.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.riantono.weather.R
import com.riantono.weather.ui.entity.ForecastModel
import com.riantono.weather.ui.fragments.citydetail.adapters.viewholders.ForecastListViewHolder
import javax.inject.Inject

class ForecastListAdapter @Inject constructor(context: Context) : RecyclerView.Adapter<ForecastListViewHolder>() {
    val listOfForecast = ArrayList<ForecastModel>()
    val context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_forecast_list, parent, false)
        return ForecastListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfForecast.size
    }

    override fun onBindViewHolder(holder: ForecastListViewHolder, position: Int) {
        holder.bindData(context,listOfForecast[position])
    }

    fun setList(listForecast: List<ForecastModel>?) {
        if (listForecast != null) {
            this.listOfForecast.addAll(listForecast)
            notifyDataSetChanged()
        }
    }

}