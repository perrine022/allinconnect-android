package com.allinconnect.app.core.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationService @Inject constructor(
    private val context: Context
) {
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    
    private val _authorizationStatus = MutableStateFlow<Int>(PackageManager.PERMISSION_DENIED)
    val authorizationStatus: StateFlow<Int> = _authorizationStatus.asStateFlow()
    
    private val _currentLocation = MutableStateFlow<Location?>(null)
    val currentLocation: StateFlow<Location?> = _currentLocation.asStateFlow()
    
    private val _locationError = MutableStateFlow<String?>(null)
    val locationError: StateFlow<String?> = _locationError.asStateFlow()
    
    init {
        checkPermissionStatus()
    }
    
    fun checkPermissionStatus() {
        val hasPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        
        _authorizationStatus.value = if (hasPermission) {
            PackageManager.PERMISSION_GRANTED
        } else {
            PackageManager.PERMISSION_DENIED
        }
    }
    
    fun requestLocationPermission() {
        // Permission request should be handled in Activity/Fragment
        checkPermissionStatus()
    }
    
    fun startLocationUpdates() {
        if (_authorizationStatus.value != PackageManager.PERMISSION_GRANTED) {
            return
        }
        
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            10000L // 10 seconds
        ).build()
        
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                _currentLocation.value = result.lastLocation
                _locationError.value = null
            }
        }
        
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            context.mainLooper
        )
    }
    
    fun stopLocationUpdates() {
        // LocationCallback should be stored and removed
    }
    
    fun calculateDistance(from: Location, to: Location): Double {
        return from.distanceTo(to) / 1000.0 // Distance in kilometers
    }
}
