package com.recyclego.userapp.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.musalasoft.weatherapp.di.*
import com.musalasoft.weatherapp.event.ShowToastEvent
import com.musalasoft.weatherapp.model.ErrorHandle
import com.musalasoft.weatherapp.viewmodel.HomeViewModel
import org.greenrobot.eventbus.EventBus
import retrofit2.HttpException


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

    fun errorHandle(error: Throwable?) {
        //getting response body if status is not 200
        if (error != null) {
            error.printStackTrace()
            if (error is HttpException) {
                val error = error as HttpException
                var errorBody = ""
                try {
                    errorBody = error.response().errorBody()!!.string()
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
                val errorHandle = Gson().fromJson(errorBody, ErrorHandle::class.java)

                if(errorHandle != null){
                    val errorMessage = errorHandle.message

                    if (!errorMessage.isNullOrEmpty()) {
                        EventBus.getDefault().post(ShowToastEvent("Error: $errorMessage"))
                    }
                }
            } else {
                EventBus.getDefault()
                        .post(ShowToastEvent("Something went wrong with error: " + error.localizedMessage))
            }
        }
    }

}