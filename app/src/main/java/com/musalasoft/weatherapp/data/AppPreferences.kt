package com.musalasoft.weatherapp.data

import android.content.Context
import android.content.SharedPreferences


class AppPreferences(context: Context) {
    val PREFS_FILENAME = "com.musalasoft.weatherapp"
    val TOKEN = "token"
    val LANGUAGE = "language"
    val FIREBASETOKEN = "firebasetoken"
    val LATITUDE = "latitude"
    val LONGITUDE = "longitude"
    val WORK_ORDER_ID = "work_order_id"
    val LATITUDE_OF_DRIVER = "latitude_of_driver"
    val LONGITUDE_OF_DRIVER = "longitude_of_driver"
    val JOB_ORDER_ID = "job_order_id"


    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0);

    var token: String?
        get() = prefs.getString(TOKEN,null)
        set(value) = prefs.edit().putString(TOKEN, value).apply()

//    var firebasetoken: String
//        get() =prefs.getString(FIREBASETOKEN,"")
//        set(value) = prefs.edit().putString(FIREBASETOKEN, value).apply()


    var latitude: String?
        get() = prefs.getString(LATITUDE,null)
        set(value) = prefs.edit().putString(LATITUDE, value).apply()
    var longitude: String?
        get() = prefs.getString(LONGITUDE,null)
        set(value) = prefs.edit().putString(LONGITUDE, value).apply()

    var latitudeOfDriver: String?
        get() = prefs.getString(LATITUDE_OF_DRIVER,null)
        set(value) = prefs.edit().putString(LATITUDE_OF_DRIVER, value).apply()
    var longitudeOfDriver: String?
        get() = prefs.getString(LONGITUDE_OF_DRIVER,null)
        set(value) = prefs.edit().putString(LONGITUDE_OF_DRIVER, value).apply()


    var workOrderId: Int
        get() = prefs.getInt(WORK_ORDER_ID,-1)
        set(value) = prefs.edit().putInt(WORK_ORDER_ID, value).apply()

    var jobOrderId: Int
        get() = prefs.getInt(JOB_ORDER_ID,-1)
        set(value) = prefs.edit().putInt(JOB_ORDER_ID, value).apply()


    fun deletePrefs() {
        prefs.edit().clear().apply()
    }
//
//    var language: String
//        get() =prefs.getString(LANGUAGE,"")
//        set(value) = prefs.edit().putString(LANGUAGE, value).apply()
}