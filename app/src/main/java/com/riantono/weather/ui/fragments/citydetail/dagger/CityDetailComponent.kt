package com.riantono.weather.ui.fragments.citydetail.dagger

import com.riantono.weather.dagger.component.AppComponent
import com.riantono.weather.ui.fragments.citydetail.CityDetailFragment
import com.riantono.weather.ui.fragments.main.MainFragment
import dagger.Component


@CityDetailScope
@Component(modules = [CityDetailModule::class], dependencies = [AppComponent::class])
interface CityDetailComponent {
    fun inject(context: CityDetailFragment)
}