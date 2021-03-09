package com.musalasoft.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.musalasoft.weatherapp.api.ApiService
import com.musalasoft.weatherapp.data.AppPreferences

import com.recyclego.userapp.utils.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel : BaseViewModel() {
    @Inject
    lateinit var apiService: ApiService
    @Inject
    lateinit var preferences: AppPreferences

   // val list = MutableLiveData<List<JobOrdersResponse>>()

    private lateinit var subscription: Disposable
    val loading = MutableLiveData<Boolean>()

    var isNetworkAvailable = MutableLiveData<Boolean>()


//    fun getJobOrders(currentDate: String) {
//        //  if (isNetworkAvailable.value == true) {
//
//        subscription = apiService.getJobOrders(preferences.token.toString(), currentDate)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnSubscribe { loading.value = true }
//            .doOnTerminate { loading.value = false }
//            .subscribe({ result ->
//
//                list.value = result
//
//
//            }, { e ->
//                e.printStackTrace()
//                EventBus.getDefault()
//                    .post(ShowToastEvent("Something went wrong with error: " + e.localizedMessage))
//                if (e != null && e.localizedMessage != null && e.localizedMessage.contains(
//                        "Unauthorized",
//                        true
//                    )
//                ) {
//                    EventBus.getDefault().post(LogoutUserEvent())
//                }
//
//            })
//    }
//            else{
//              EventBus.getDefault().post(ShowToastEvent("No internet connection"))
//          }



//    fun openMenu() {
//        EventBus.getDefault().post(OpenMenuEvent())
//    }
//
//    fun closeMenu() {
//        EventBus.getDefault().post(CloseMenuEvent())
//
//    }

//    fun onLogoutClicked() {
//
//
//        println("Tokeni: kur bon logout" + preferences.token)
//
//
//        subscription = apiService.logout(preferences.token.toString())
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnSubscribe { loading.value = true}
//            .doOnTerminate { loading.value = false}
//            .subscribe({ result ->
//
//                preferences.deletePrefs()
//                EventBus.getDefault().post(OpenActivityEvent(LoginActivity()))
//                EventBus.getDefault().post(CloseMenuEvent())
//
//
//            }, { e ->
//                e.printStackTrace()
//
//                EventBus.getDefault().post(ShowToastEvent("Something went wrong with error: " + e.localizedMessage))
//
//                if (e != null && e.localizedMessage != null && e.localizedMessage.contains("Unauthorized",true)) {
//                    EventBus.getDefault().post(LogoutUserEvent())
//                }
//
//            })
//
//
//    }
//
//    fun updateFirebaseToken(updateFirebaseTokenBody: UpdateFirebaseTokenBody) {
//        if (preferences.token != null) {
//            subscription = apiService.updateFirebaseToken(preferences.token.toString(), "application/json", updateFirebaseTokenBody)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe { }
//                .doOnTerminate { }
//                .subscribe({ result ->
//
//                }, { e ->
//                    e.printStackTrace()
//                    if (e != null && e.localizedMessage != null && e.localizedMessage.contains(
//                            "Unauthorized",
//                            true
//                        )
//                    ) {
//                        EventBus.getDefault().post(LogoutUserEvent())
//                    }
//                })
//        }
//    }


}