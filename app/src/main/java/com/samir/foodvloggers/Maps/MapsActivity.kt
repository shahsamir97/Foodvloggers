package com.samir.foodvloggers

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.common.api.Status
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
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.parse.ParseInstallation


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, PlaceSelectionListener {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

         Places.initialize(this, "AIzaSyDD7QekSVaYeVi74hEsGSIj9ZwEfe9SN_g")
        val placesClient = Places.createClient(this)



        //initialize autocomplete fragment
        val autocompleteFragment = this.supportFragmentManager.findFragmentById(R.id.autocomplete_fragment)
                as? (AutocompleteSupportFragment)


        autocompleteFragment?.setTypeFilter(TypeFilter.ESTABLISHMENT)
        autocompleteFragment?.setCountries("BD")
        //autocompleteFragment?.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
        autocompleteFragment?.setPlaceFields(listOf(Place.Field.ADDRESS, Place.Field.LAT_LNG))
        autocompleteFragment?.setOnPlaceSelectedListener(this)
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

    }

    override fun onPlaceSelected(p0: Place) {

        Toast.makeText(this,""+ p0.name+p0!!.latLng, Toast.LENGTH_LONG).show();
        Log.i("1223", p0.latLng.toString())
        val latLng: LatLng? = p0.latLng
        Log.i("4444",latLng?.latitude.toString() + "place :"+p0.address.toString())
        mMap.addMarker(latLng?.let { MarkerOptions().position(it).title(p0.name) })
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14F))

    }

    override fun onError(p0: Status) {

        Toast.makeText(this,""+p0.toString(), Toast.LENGTH_LONG).show();
        Log.i("1223", p0.toString())

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i("1223","Activity Result Called")
    }

}