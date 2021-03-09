package com.musalasoft.weatherapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton



@Singleton
@Component(
    modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    NetworkModule::class,
    ActivityBuilder::class
    ]
)

interface AppComponent : AndroidInjector<App>{
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application): Builder
        fun appModule(appModule: AppModule): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun build(): AppComponent
    }
}