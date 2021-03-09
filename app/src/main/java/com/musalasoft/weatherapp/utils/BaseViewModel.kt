package com.recyclego.userapp.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import com.musalasoft.weatherapp.di.*
import com.musalasoft.weatherapp.viewmodel.HomeViewModel


abstract class BaseViewModel : ViewModel(){
    val Context.app: App
    get() = applicationContext as App

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .create(App.instance)
        .networkModule(NetworkModule)
        .appModule(AppModule)
        .build()


    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject(){
        when (this) {

             is HomeViewModel -> injector.inject(this)

        }
    }

}