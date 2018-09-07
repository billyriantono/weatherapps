package com.riantono.weather.ui.fragments.main.dagger

import com.riantono.weather.dagger.component.AppComponent
import com.riantono.weather.ui.fragments.main.MainFragment
import dagger.Component


@MainScope
@Component(modules = [MainModule::class], dependencies = [AppComponent::class])
interface MainComponent {
    fun inject(context: MainFragment)
}