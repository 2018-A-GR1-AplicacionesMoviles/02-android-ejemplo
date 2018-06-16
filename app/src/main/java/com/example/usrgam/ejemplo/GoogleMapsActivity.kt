package com.example.usrgam.ejemplo

import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_google_maps.*
import java.util.*

class GoogleMapsActivity :
        AppCompatActivity(),
        OnMapReadyCallback,
        GoogleMap.OnCameraMoveStartedListener,
        GoogleMap.OnCameraMoveListener,
        GoogleMap.OnCameraMoveCanceledListener,
        GoogleMap.OnCameraIdleListener,
        GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener {

    private lateinit var mMap: GoogleMap

    var arregloMarcadores = ArrayList<Marker>()

    val epnLatLang = LatLng(-0.2103764, -78.4891095)

    val zoom = 17f

    val casaCulturaLatLang = LatLng(-0.209990, -78.495118)
    val casaCultura: CameraPosition = CameraPosition
            .Builder()
            .target(casaCulturaLatLang)
            .zoom(zoom)
            .build()

    val supermaxiLatLang = LatLng(-0.206450, -78.487270)
    val supermaxi: CameraPosition = CameraPosition
            .Builder()
            .target(supermaxiLatLang)
            .zoom(zoom)
            .build()


    var usuarioTienePermisosLocalizacion = false;
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_maps)

        solicitarPermisosLocalizacion()


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        with(googleMap) {
            var polyline1 = googleMap.addPolyline(PolylineOptions()
                    .clickable(true)
                    .add(

                            LatLng(-0.210462, -78.493948),
                            LatLng(-0.208218, -78.490163),
                            LatLng(-0.208583, -78.488940),
                            LatLng(-0.209377, -78.490303)
                    )
            )

            var polygon1 = googleMap.addPolygon(PolygonOptions()
                    .clickable(true)
                    .add(
                            LatLng(-0.209431, -78.490078),
                            LatLng(-0.208734, -78.488951),
                            LatLng(-0.209431, -78.488286),
                            LatLng(-0.210085, -78.489745)


                    )

            )

            polygon1.tag = "alpha"

            formatearEstiloPoligono(polygon1)


            establecerListeners(googleMap)
            establecerSettings(googleMap)


            anadirMarcador(epnLatLang, "Ciudad de quito")




            moverCamaraPorLatLongZoom(epnLatLang, zoom)


            button_quito_julio_andrade.setOnClickListener { v ->
                anadirMarcador(casaCulturaLatLang, "Marcador en Quito Julio Andrade")
                moverCamaraPorPosicion(casaCultura)
            }

            button_quito.setOnClickListener { v ->
                anadirMarcador(supermaxiLatLang, "Marcador en Quito Julio Andrade")
                moverCamaraPorPosicion(supermaxi)
            }
        }
    }

    fun solicitarPermisosLocalizacion() {
        if (ContextCompat.checkSelfPermission(this.applicationContext,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            usuarioTienePermisosLocalizacion = true
        } else {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
    }

    private fun establecerSettings(googleMap: GoogleMap) {
        with(googleMap) {
            uiSettings.isZoomControlsEnabled = false
            uiSettings.isMyLocationButtonEnabled = true
        }
    }

    private fun anadirMarcador(latitudLongitud: LatLng, titulo: String) {

        arregloMarcadores.forEach { marker: Marker ->
            marker.remove()
        }

        arregloMarcadores = ArrayList<Marker>()

        val marker = mMap.addMarker(
                MarkerOptions()
                        .position(latitudLongitud)
                        .title(titulo)
        )

        arregloMarcadores.add(marker)

        Log.i("map-adrian", "$arregloMarcadores")
    }


    private fun moverCamaraPorLatLongZoom(latitudLongitud: LatLng, zoom: Float) {


        mMap.moveCamera(
                CameraUpdateFactory
                        .newLatLngZoom(latitudLongitud, zoom)
        )


    }


    private fun moverCamaraPorPosicion(posicionCamara: CameraPosition) {
        mMap.moveCamera(
                CameraUpdateFactory
                        .newCameraPosition(posicionCamara)
        )
    }


    private fun establecerListeners(googleMap: GoogleMap) {
        with(googleMap) {

            setOnCameraIdleListener(this@GoogleMapsActivity)
            setOnCameraMoveStartedListener(this@GoogleMapsActivity)
            setOnCameraMoveListener(this@GoogleMapsActivity)
            setOnCameraMoveCanceledListener(this@GoogleMapsActivity)


            setOnPolylineClickListener(this@GoogleMapsActivity)
            setOnPolygonClickListener(this@GoogleMapsActivity)
        }
    }


    private fun formatearEstiloPoligono(polygon: Polygon) {
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


    override fun onCameraMoveStarted(p0: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCameraMove() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCameraMoveCanceled() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCameraIdle() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPolylineClick(p0: Polyline?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPolygonClick(p0: Polygon?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
