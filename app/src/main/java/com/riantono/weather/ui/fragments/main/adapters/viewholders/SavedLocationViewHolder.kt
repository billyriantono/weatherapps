package com.riantono.weather.ui.fragments.main.adapters.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.riantono.weather.R
import com.riantono.weather.data.entity.Location
import com.riantono.weather.ui.fragments.main.adapters.SavedLocationAdapter
import kotlinx.android.synthetic.main.item_city_list.view.*

class SavedLocationViewHolder(itemView: View, cityClickListener: SavedLocationAdapter.OnCityClickListener?) : RecyclerView.ViewHolder(itemView) {
    private val textViewCityName: TextView = itemView.findViewById(R.id.tv_city_name)
    private val onCityClickListener: SavedLocationAdapter.OnCityClickListener? = cityClickListener

    private var location: Location? = null

    init {
        itemView.setOnClickListener {
            onCityClickListener?.let { onCityClickListener -> onCityClickListener.onClick(location) }
        }
    }

    fun bindData(location: Location) {
        this.location = location
        textViewCityName.text = location.address
    }

}

