package com.samir.foodvloggers

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.samir.foodvloggers.databinding.ActivityPlacesBinding
import java.util.*

class PlacesActivity_UNSED_NOW : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityPlacesBinding
    val AUTOCOMPLETE_REQUEST_CODE = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_places)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val fields: List<Place.Field> =
            listOf(Place.Field.NAME)

        Places.initialize(this, "AIzaSyDD7QekSVaYeVi74hEsGSIj9ZwEfe9SN_g")
        val placesClient =
            Places.createClient(this)

        binding.mapSearchBar.setOnClickListener {
            val intent: Intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                .setTypeFilter(TypeFilter.ESTABLISHMENT)
                .setCountry("BD")
                .build(this)
            startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
            AutocompleteSessionToken.newInstance()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val place: Place? = data?.let { Autocomplete.getPlaceFromIntent(it)}  // change made

                mMap.addMarker(place?.latLng?.let { MarkerOptions().position(it) })
                mMap.moveCamera(CameraUpdateFactory.newLatLng(place?.latLng))
                mMap.animateCamera(CameraUpdateFactory.zoomTo(13F))
                AutocompleteSessionToken.newInstance()



                /*Log.i( "Place: " , place?.getName().toString() + place?.address.toString());*/
               // Log.i("locations",latlng2.get(0)+" " + latlng2.get(1))
              /*  mMap.addMarker(location?.let { MarkerOptions().position(it).title("Marker in Sydney") })
                mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
                mMap.animateCamera(CameraUpdateFactory.zoomTo(14F))*/

            }else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                val status = data?.let { Autocomplete.getStatusFromIntent(it) }
                Log.e("API_ ERROR", status.toString())
            }else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("Place","Activity Closed")
            }
        }
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
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14F))
    }
}