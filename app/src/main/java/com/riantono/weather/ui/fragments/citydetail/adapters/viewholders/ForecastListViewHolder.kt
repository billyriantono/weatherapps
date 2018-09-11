package com.riantono.weather.ui.fragments.citydetail.adapters.viewholders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.riantono.weather.R
import com.riantono.weather.ui.entity.ForecastModel
import com.riantono.weather.utils.DateUtil

class ForecastListViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    val tvWeatherDegree = itemView?.findViewById<TextView>(R.id.tv_weather_degree)
    val tvWeatherDate = itemView?.findViewById<TextView>(R.id.tv_date)


    fun bindData(context: Context, forecastModel: ForecastModel) {
        tvWeatherDegree?.text = context.resources.getString(R.string.degree_placeholder, forecastModel.temp.toString())
        if (forecastModel.dt != null) {
            tvWeatherDate?.text = DateUtil.formatDate(forecastModel.dt)
        } else {
            tvWeatherDate?.text = "Unknown Date"
        }
    }
}