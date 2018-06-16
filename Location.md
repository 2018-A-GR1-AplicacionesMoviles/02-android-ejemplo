# Location

## Manifest

```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

## Gradle

```gradle
implementation 'com.google.android.gms:play-services-location:15.0.1'

```


## Activity

```kotlin
class MainActivity : AppCompatActivity() {
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var locationCallback: LocationCallback
    val PERMISO_REQUEST = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), PERMISO_REQUEST)
        } else {
            iniciar()
        }

    }

    private fun buildLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 5000
        locationRequest.fastestInterval = 3000
        locationRequest.smallestDisplacement = 10f
    }

    private fun buildLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult?) {
                super.onLocationResult(p0)
                val location = p0!!.locations.get(p0!!.locations.size - 1) // obtener la localizacion
                Log.i("location", "${location.latitude} / ${location.longitude}")
            }
        }
    }

    private fun parar() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    private fun iniciar(){
        buildLocationRequest()
        buildLocationCallback()
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        val tienePermisosFineLocation = ActivityCompat.checkSelfPermission(this@MainActivity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED;
        val tienePermisosCoarseLocation = ActivityCompat.checkSelfPermission(this@MainActivity, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;
        if (tienePermisosFineLocation && tienePermisosCoarseLocation) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION), PERMISO_REQUEST)
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
        }
    }

}
```
