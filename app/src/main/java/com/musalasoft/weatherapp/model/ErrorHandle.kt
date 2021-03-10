package com.musalasoft.weatherapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ErrorHandle(
    @SerializedName("cod")
    @Expose
    var cod: String? = null,

    @SerializedName("message")
    @Expose
    var message: String? = null
)







