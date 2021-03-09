package com.musalasoft.weatherapp.di

import android.content.Context
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App: DaggerApplication(){
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .create(this)
            .appModule(AppModule)
            .networkModule(NetworkModule)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object{
        lateinit var instance: App
    }
}