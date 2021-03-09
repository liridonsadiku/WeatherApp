package com.musalasoft.weatherapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Coord(
    @SerializedName("lon")
    @Expose
    var lon: Float? = null,

    @SerializedName("lat")
    @Expose
    var lat: Float? = null
)




