package com.emranul.googlemapprectice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.emranul.googlemapprectice.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.MapStyleOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    companion object{
        private const val TAG = "MapsActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,10f) )

        customMapSle(googleMap)


    }

    private fun customMapSle(googleMap: GoogleMap) {

        try {
            val success = googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this,
                    R.raw.map_style
                )
            )
            if (!success) {
                Log.d(TAG, "mapStyle: error to style")
            }
        }catch (e:Exception){
            Log.e(TAG, "mapStyle: ", e)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_style_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.hybrid_map ->{
                map.mapType = GoogleMap.MAP_TYPE_HYBRID
            }
            R.id.terrain_map ->{
                map.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }
            R.id.satellite_map ->{
                map.mapType = GoogleMap.MAP_TYPE_SATELLITE
            }
            R.id.normal_map ->{
                map.mapType = GoogleMap.MAP_TYPE_NORMAL
            }
            R.id.none_map ->{
                map.mapType = GoogleMap.MAP_TYPE_NONE
            }
        }

        return true
    }
}