package com.musalasoft.weatherapp.ui

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.location.*
import com.musalasoft.weatherapp.R
import com.musalasoft.weatherapp.databinding.ActivityMainBinding
import com.musalasoft.weatherapp.viewmodel.HomeViewModel
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions


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
        getLastLocation()
    }


    @SuppressLint("MissingPermission")
    private fun getLastLocation() {

        if (EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION,
                                                         Manifest.permission.ACCESS_COARSE_LOCATION,
                                                         Manifest.permission.INTERNET)) {
                observeViewModel()
                mFusedLocationClient.lastLocation.addOnCompleteListener { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        binding.tvCurrentLocation.text = location.latitude.toString() +" njeshi " + location.longitude.toString()
                    }

                }
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.enable_location),
                    RC_SMS_PERM, Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.INTERNET);
        }
    }

    private fun observeViewModel() {

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

}