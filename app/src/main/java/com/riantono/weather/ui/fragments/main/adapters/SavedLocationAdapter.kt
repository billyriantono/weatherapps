package com.riantono.weather.ui.fragments.main.adapters

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.riantono.weather.R
import com.riantono.weather.data.entity.Location
import com.riantono.weather.ui.fragments.main.adapters.viewholders.SavedLocationViewHolder

class SavedLocationAdapter(onCityClickListener: OnCityClickListener) : PagedListAdapter<Location, SavedLocationViewHolder>(DIFF_CALLBACK) {
    private var onCityClickListener: OnCityClickListener = onCityClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedLocationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city_list, parent, false)
        return SavedLocationViewHolder(view, onCityClickListener)
    }

    override fun onBindViewHolder(holder: SavedLocationViewHolder, position: Int) {
        val location = getItem(position)
        if (location != null) {
            holder.bindData(location)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Location>() {
            override fun areItemsTheSame(oldItem: Location?, newItem: Location?): Boolean = oldItem?.id == newItem?.id
            override fun areContentsTheSame(oldItem: Location?, newItem: Location?): Boolean = oldItem?.latitude == newItem?.latitude && oldItem?.longitude == newItem?.longitude
        }
    }

    interface OnCityClickListener {
        fun onClick(Location: Location?)
    }
}