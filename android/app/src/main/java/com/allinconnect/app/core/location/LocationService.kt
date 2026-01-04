package com.allinconnect.app.core.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationService @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    
    private val _authorizationStatus = MutableStateFlow<LocationPermissionStatus>(LocationPermissionStatus.NOT_DETERMINED)
    val authorizationStatus: StateFlow<LocationPermissionStatus> = _authorizationStatus.asStateFlow()
    
    private val _currentLocation = MutableStateFlow<Location?>(null)
    val currentLocation: StateFlow<Location?> = _currentLocation.asStateFlow()
    
    private val _locationError = MutableStateFlow<String?>(null)
    val locationError: StateFlow<String?> = _locationError.asStateFlow()
    
    init {
        checkPermissionStatus()
    }
    
    fun checkPermissionStatus() {
        val hasFineLocation = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        
        val hasCoarseLocation = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        
        _authorizationStatus.value = when {
            hasFineLocation || hasCoarseLocation -> LocationPermissionStatus.GRANTED
            else -> LocationPermissionStatus.DENIED
        }
    }
    
    fun requestLocationPermission() {
        checkPermissionStatus()
        // La permission doit être demandée depuis l'Activity/Fragment
        // Cette méthode vérifie juste le statut
    }
    
    fun startLocationUpdates() {
        if (_authorizationStatus.value != LocationPermissionStatus.GRANTED) {
            return
        }
        
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000)
            .setMinUpdateIntervalMillis(5000)
            .build()
        
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.lastLocation?.let { location ->
                    _currentLocation.value = location
                    _locationError.value = null
                }
            }
        }
        
        try {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                context.mainLooper
            )
        } catch (e: SecurityException) {
            _locationError.value = "Permission de localisation refusée"
        }
    }
    
    fun stopLocationUpdates() {
        // Nécessite un LocationCallback pour arrêter, à implémenter si nécessaire
    }
    
    fun calculateDistance(from: Location, to: Location): Double {
        return from.distanceTo(to) / 1000.0 // Distance en kilomètres
    }
}

enum class LocationPermissionStatus {
    NOT_DETERMINED,
    GRANTED,
    DENIED
}

