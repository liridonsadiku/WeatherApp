package com.musalasoft.weatherapp.di

import android.app.Application
import com.musalasoft.weatherapp.data.AppPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Liridon Sadiku on 20,January,2020
 */

@Module
object AppModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): AppPreferences = AppPreferences(app)
}