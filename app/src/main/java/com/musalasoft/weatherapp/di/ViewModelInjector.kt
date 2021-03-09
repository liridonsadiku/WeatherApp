package com.musalasoft.weatherapp.di

import android.app.Application
import com.musalasoft.weatherapp.viewmodel.HomeViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(NetworkModule::class, AppModule::class))
interface ViewModelInjector {

    //inject here
    fun inject(homeViewModel: HomeViewModel)


    @Component.Builder
    interface Builder{
        @BindsInstance
        fun create(app: Application): Builder

        fun appModule(appModule: AppModule): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun build(): ViewModelInjector
    }
}