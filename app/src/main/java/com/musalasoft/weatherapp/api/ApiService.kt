package com.musalasoft.weatherapp.api

import com.musalasoft.weatherapp.model.Weather
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

interface ApiService {


    @GET("weather")
    fun getCurrentWeatherBasedOnCity(@Query("q") cityName: String,
                                    @Query("appid") appId: String,
                                    @Query("units") unit: String): Observable<Weather>


}