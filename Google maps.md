# Google Maps

## Requisitos 
Para usar los mapas necesitamos el api key y el paquete de google play services. El api key es sencillo de conseguir con un link cuando creamos una nueva actividad de mapa en este archivo: `google_maps_api.xml`
Para instalar el paquete de google play services debesmos de ir a `sdk manager`>`sdk tools`>`Google Play services`

## Manifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.currentplacedetailsonmap">

    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">

        <meta-data
            android:name="com.google.android.gms.version"
            android:value="@integer/google_play_services_version" />
        <!--
             The API key for Google Maps-based APIs.
        -->
        <meta-data
            android:name="com.google.android.geo.API_KEY"
            android:value="@string/google_maps_key" />

        <activity
            android:name="com.example.currentplacedetailsonmap.MapsActivityCurrentPlace"
            android:label="@string/title_activity_maps">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```
## Layout

```xml
<?xml version="1.0" encoding="utf-8"?>

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:map="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <fragment
            android:id="@+id/map"
            android:name="com.google.android.gms.maps.SupportMapFragment"
            android:layout_width="match_parent"
            android:layout_height="287dp"
            android:layout_alignParentStart="true"
            android:layout_alignParentTop="true"
            android:layout_marginTop="224dp"
            tools:context=".MapsActivity" />

        <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="222dp"
            android:layout_alignParentStart="true"
            android:layout_alignParentTop="true"
            android:layout_marginTop="0dp">

            <Button
                android:id="@+id/button_quito"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_alignParentStart="true"
                android:layout_alignParentTop="true"
                android:layout_marginStart="59dp"
                android:layout_marginTop="33dp"
                android:text="Quito" />

            <Button
                android:id="@+id/button_quito_julio_andrade"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_alignParentBottom="true"
                android:layout_alignParentStart="true"
                android:layout_marginBottom="44dp"
                android:layout_marginStart="50dp"
                android:text="Julio Andrade" />

        </RelativeLayout>

    </RelativeLayout>

</LinearLayout>


```

## Activity

```kotlin
package com.layouts.adrian.googlemaps

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.maps.*

import kotlinx.android.synthetic.main.activity_maps.*
import java.time.ZoneId
import android.widget.Toast
import com.google.android.gms.maps.model.*
import com.google.android.gms.maps.model.Gap
import com.google.android.gms.maps.model.PatternItem
import com.google.android.gms.maps.model.Dash
import com.google.android.gms.maps.model.Dot
import android.icu.lang.UProperty.DASH
import java.util.*
import java.util.Arrays.asList
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.support.annotation.NonNull


class MapsActivity :
        AppCompatActivity(),
        GoogleMap.OnCameraMoveStartedListener,
        GoogleMap.OnCameraMoveListener,
        GoogleMap.OnCameraMoveCanceledListener,
        GoogleMap.OnCameraIdleListener,
        OnMapReadyCallback,
        GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener {

    private lateinit var mMap: GoogleMap
    private var mMarkerArray = ArrayList<Marker>()
    private val quito: LatLng = LatLng(-0.201792, -78.502413)
    private val quito_julio_andrade = LatLng(-0.201237, -78.496536)
    private val quito_julio_andrade_camera: CameraPosition = CameraPosition.Builder()
            .target(LatLng(-0.201237, -78.496536))
            .zoom(14f)
//            .bearing(300f)
//            .tilt(50f)
            .build()
    private val quito_camera: CameraPosition = CameraPosition.Builder()
            .target(LatLng(-0.201792, -78.502413))
            .zoom(14f)
//            .bearing(300f)
//            .tilt(50f)
            .build()

    private val ZOOM_LEVEL: Float = 14f


    // parametros del poligono

    private val COLOR_BLACK_ARGB = -0x1000000
    private val COLOR_WHITE_ARGB = -0x1
    private val COLOR_GREEN_ARGB = -0xc771c4
    private val COLOR_PURPLE_ARGB = -0x7e387c
    private val COLOR_ORANGE_ARGB = -0xa80e9
    private val COLOR_BLUE_ARGB = -0x657db

    private val POLYGON_STROKE_WIDTH_PX: Float = 8.toFloat()
    private val PATTERN_DASH_LENGTH_PX = 20
    private val PATTERN_GAP_LENGTH_PX = 20
    private val DOT = Dot()
    private val DASH = Dash(PATTERN_DASH_LENGTH_PX.toFloat())
    private val GAP = Gap(PATTERN_GAP_LENGTH_PX.toFloat())

    private val PATTERN_POLYGON_ALPHA = Arrays.asList(GAP, DASH)

    // Create a stroke pattern of a dot followed by a gap, a dash, and another gap.
    private val PATTERN_POLYGON_BETA = Arrays.asList(DOT, GAP, DASH, GAP)
    private var mLocationPermissionGranted = false

    private var mLastKnownLocation: LatLng? = null

    private fun stylePolygon(polygon: Polygon) {
        var type = ""
        // Get the data object stored with the polygon.
        if (polygon.tag != null) {
            type = polygon.tag.toString()
        }

        var pattern: List<PatternItem>? = null
        var strokeColor = COLOR_BLACK_ARGB
        var fillColor = COLOR_WHITE_ARGB

        when (type) {
        // If no type is given, allow the API to use the default.
            "alpha" -> {
                // Apply a stroke pattern to render a dashed line, and define colors.
                pattern = PATTERN_POLYGON_ALPHA
                strokeColor = COLOR_GREEN_ARGB
                fillColor = COLOR_PURPLE_ARGB
            }
            "beta" -> {
                // Apply a stroke pattern to render a line of dots and dashes, and define colors.
                pattern = PATTERN_POLYGON_BETA
                strokeColor = COLOR_ORANGE_ARGB
                fillColor = COLOR_BLUE_ARGB
            }
        }

        polygon.strokePattern = pattern
        polygon.setStrokeWidth(POLYGON_STROKE_WIDTH_PX)
        polygon.strokeColor = strokeColor
        polygon.fillColor = fillColor
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        with(googleMap) {
            var polyline1 = googleMap.addPolyline(PolylineOptions()
                    .clickable(true)
                    .add(
                            LatLng(-0.197585, -78.502124),
                            LatLng(-0.198175, -78.499903),
                            LatLng(-0.199229, -78.501023)
                    )
            )

            var polygon1 = googleMap.addPolygon(PolygonOptions()
                    .clickable(true)
                    .add(
                            LatLng(-0.199862, -78.503383),
                            LatLng(-0.201300, -78.503061),
                            LatLng(-0.199754, -78.502677)
                    )

            )

            polygon1.tag = "alpha"

            stylePolygon(polygon1)


            establecerListeners(googleMap)
            establecerSettings(googleMap)
            anadirMarcador(quito, "Ciudad de quito")
            moverCamaraPorLatLongZoom(quito, ZOOM_LEVEL)
            button_quito_julio_andrade.setOnClickListener { v ->
                anadirMarcador(quito_julio_andrade, "Marcador en Quito Julio Andrade")
                moverCamaraPorPosicion(quito_julio_andrade_camera)
            }

            button_quito.setOnClickListener { v ->
                anadirMarcador(quito, "Marcador en Quito Julio Andrade")
                moverCamaraPorPosicion(quito_camera)
            }
        }
    }

    private fun establecerListeners(googleMap: GoogleMap) {
        with(googleMap) {
            setOnCameraIdleListener(this@MapsActivity)
            setOnCameraMoveStartedListener(this@MapsActivity)
            setOnCameraMoveListener(this@MapsActivity)
            setOnCameraMoveCanceledListener(this@MapsActivity)
            setOnPolylineClickListener(this@MapsActivity)
            setOnPolygonClickListener(this@MapsActivity)
        }
    }

    override fun onPolygonClick(p0: Polygon?) {
        Toast.makeText(this, "Dio click adentro ",
                Toast.LENGTH_SHORT).show()
    }

    override fun onPolylineClick(polyline: Polyline) {
        // Flip from solid stroke to dotted stroke pattern.
//        if (polyline.pattern == null || !polyline.pattern!!.contains(DOT)) {
//            polyline.pattern = PATTERN_POLYLINE_DOTTED
//        } else {
//            // The default pattern is a solid stroke.
//            polyline.pattern = null
//        }

        Toast.makeText(this, "Dio click en linea ",
                Toast.LENGTH_SHORT).show()
    }

    private fun anadirMarcador(latitudLongitud: LatLng, titulo: String) {
        mMarkerArray.forEach { marker: Marker ->
            marker.remove()
        }
        mMarkerArray = ArrayList<Marker>()
        val marker = mMap.addMarker(MarkerOptions().position(latitudLongitud).title(titulo))
        mMarkerArray.add(marker)
        Log.i("map-adrian", "$mMarkerArray")
    }

    private fun moverCamaraPorPosicion(posicionCamara: CameraPosition) {
        changeCamera(CameraUpdateFactory.newCameraPosition(posicionCamara))
    }

    private fun moverCamaraPorLatLongZoom(latitudLongitud: LatLng, zoom: Float) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latitudLongitud, zoom))
    }

    private fun eliminarMarcador() {

    }

    private fun establecerSettings(googleMap: GoogleMap) {
        with(googleMap) {
            uiSettings.isZoomControlsEnabled = false
            uiSettings.isMyLocationButtonEnabled = true
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        mLocationPermissionGranted = false
        when (requestCode) {
            1 -> {
                // If request is cancelled, the result arrays are empty.
                Toast.makeText(this, "SEXO ANAAL",
                        Toast.LENGTH_SHORT).show()
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true

                }
            }
        }
        updateLocationUI()
    }

    private fun updateLocationUI() {
        if (mMap == null) {
            return
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.isMyLocationEnabled = true
                mMap.uiSettings.isMyLocationButtonEnabled = true
            } else {
                mMap.isMyLocationEnabled = false
                mMap.uiSettings.isMyLocationButtonEnabled = false
                mLastKnownLocation = null
                getLocationPermission()
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message)
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        getLocationPermission()
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun getLocationPermission() {
        /*
     * Request location permission, so that we can get the location of the
     * device. The result of the permission request is handled by a callback,
     * onRequestPermissionsResult.
     */
        if (ContextCompat.checkSelfPermission(this.applicationContext,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true
        } else {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
    }

    override fun onCameraMove() {
        Log.i("map-adrian", "Se esta moviendo la camara! :D")
        // When the camera is moving, add its target to the current path we'll draw on the map.
        // checkPolylineThen { addCameraTargetToPath() }
    }

    override fun onCameraMoveCanceled() {
        // When the camera stops moving, add its target to the current path, and draw it on the map.
        /*checkPolylineThen {
            addCameraTargetToPath()
            map.addPolyline(currPolylineOptions)
        }

        isCanceled = true  // Set to clear the map when dragging starts again.
        currPolylineOptions = null*/
        Log.i("map-adrian", "Se cancelo mover la camara :D")
    }

    override fun onCameraIdle() {
        /*
        checkPolylineThen {
            addCameraTargetToPath()
            map.addPolyline(currPolylineOptions)
        }

        currPolylineOptions = null
        isCanceled = false  // Set to *not* clear the map when dragging starts again.
        */
        Log.i("map-adrian", "La camara esta sin hacer nada")
    }

    private fun addCameraTargetToPath() {
        // currPolylineOptions?.add(map.cameraPosition.target)
    }

    override fun onCameraMoveStarted(reason: Int) {
//        if (!isCanceled) map.clear()
//
//
//        var reasonText = "UNKNOWN_REASON"
//        currPolylineOptions = PolylineOptions().width(5f)
//        when (reason) {
//            GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE -> {
//                currPolylineOptions?.color(Color.BLUE)
//                reasonText = "GESTURE"
//            }
//            GoogleMap.OnCameraMoveStartedListener.REASON_API_ANIMATION -> {
//                currPolylineOptions?.color(Color.RED)
//                reasonText = "API_ANIMATION"
//            }
//            GoogleMap.OnCameraMoveStartedListener.REASON_DEVELOPER_ANIMATION -> {
//                currPolylineOptions?.color(Color.GREEN)
//                reasonText = "DEVELOPER_ANIMATION"
//            }
//        }
        Log.i("map-adrian", "Empezo ")
        addCameraTargetToPath()
    }

    private fun changeCamera(update: CameraUpdate, callback: GoogleMap.CancelableCallback? = null) {
//        if (animateToggle.isChecked) {
//            if (customDurationToggle.isChecked) {
//                // The duration must be strictly positive so we make it at least 1.
//                map.animateCamera(update, Math.max(customDurationBar.progress, 1), callback)
//            } else {
//                map.animateCamera(update, callback)
//            }
//        } else {
        mMap.moveCamera(update)
//        }
    }
}

```


