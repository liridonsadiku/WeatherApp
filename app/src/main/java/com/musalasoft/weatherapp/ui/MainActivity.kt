package com.musalasoft.weatherapp.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.location.*
import com.musalasoft.weatherapp.R
import com.musalasoft.weatherapp.databinding.ActivityMainBinding
import com.musalasoft.weatherapp.event.ShowToastEvent
import com.musalasoft.weatherapp.viewmodel.HomeViewModel
import com.recyclego.userapp.utils.showToast
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.util.*


class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {
    lateinit var viewModel: HomeViewModel
    lateinit var binding: ActivityMainBinding

    private val RC_SMS_PERM = 122
    lateinit var mFusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        binding.viewModel = viewModel
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }


    @SuppressLint("MissingPermission")
    private fun getLastLocation() {

        if (EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION,
                                                         Manifest.permission.ACCESS_COARSE_LOCATION,
                                                         Manifest.permission.INTERNET)) {
            if (isLocationEnabled()) {
                observeViewModel()
                mFusedLocationClient.lastLocation.addOnCompleteListener { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        getCityNameBasedOnLocation(location)
                    }

                }
            } else {
                showEnableLocationDialog()
            }
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.enable_location),
                    RC_SMS_PERM, Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.INTERNET);
        }
    }

    private fun getCityNameBasedOnLocation(location: Location) {
        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses: List<Address> = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        var cityName = ""
        if (addresses.size>0 && addresses[0].locality != null) {
            cityName = addresses[0].locality
            binding.viewModel!!.getCurrentWather(cityName)
        }else{
            showToast(getString(R.string.can_not_get_your_current_city))
        }

        binding.tvCurrentLocation.text = location.latitude.toString() +" njeshi " + location.longitude.toString() + " city: " + cityName

    }

    private fun showEnableLocationDialog() {
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setMessage("Enable location")
                .setCancelable(false)
                .setPositiveButton("Go to Settings", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivity(intent)
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
                })

        val alert = dialogBuilder.create()
        alert.setTitle("Location Warning")
        alert.show()
    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        )
    }

    private fun observeViewModel() {

        binding.viewModel!!.loading.observe(this, androidx.lifecycle.Observer { loading ->
            loading?.let { if (it){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.GONE

            }}
        })

    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location = locationResult.lastLocation
            binding.tvCurrentLocation.text = mLastLocation.latitude.toString() +" dyshi " + mLastLocation.longitude.toString()

        }
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 5

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper()
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);

    }
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {

        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }else{
            EasyPermissions.requestPermissions(this, getString(R.string.enable_location),
                    RC_SMS_PERM,Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.INTERNET);
        }

    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        getLastLocation()
    }
    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mFusedLocationClient.removeLocationUpdates(mLocationCallback)
    }

    override fun onResume() {
        super.onResume()
        getLastLocation()
    }

    @Subscribe
    fun onEvent(message: ShowToastEvent) {
        showToast(message.message)
    }

}