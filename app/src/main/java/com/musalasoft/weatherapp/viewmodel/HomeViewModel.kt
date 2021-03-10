package com.musalasoft.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.musalasoft.weatherapp.api.ApiService
import com.musalasoft.weatherapp.data.AppPreferences
import com.musalasoft.weatherapp.event.ShowToastEvent
import com.musalasoft.weatherapp.model.Weather
import com.recyclego.userapp.utils.APP_ID
import com.recyclego.userapp.utils.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.internal.util.NotificationLite.disposable
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject


class HomeViewModel : BaseViewModel() {
    @Inject
    lateinit var apiService: ApiService
    @Inject
    lateinit var preferences: AppPreferences

    private lateinit var disposable: Disposable
    val loading = MutableLiveData<Boolean>()
    val weather = MutableLiveData<Weather>()

    fun getCurrentWather(cityName: String) {
        disposable = apiService.getCurrentWeatherBasedOnCity(cityName, APP_ID,"metric")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loading.value = true }
            .doOnTerminate { loading.value = false }
            .subscribe({ result ->
                if (result != null) {
                    weather.value = result
                }
            }, { error ->
                errorHandle(error)
            })
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }


}