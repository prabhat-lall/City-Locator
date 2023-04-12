package com.example.citylocater

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.citylocater.databinding.FragmentHomeBinding
import com.example.citylocater.databinding.FragmentLocationDetailBinding
import com.example.citylocater.models.CityLocationItem
import com.example.citylocater.utility.Constants.MAP_VIEW_BUNDLE_KEY
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson


class LocationDetailFragment : Fragment() {

    private lateinit var binding: FragmentLocationDetailBinding
    private var cityLocationItem: CityLocationItem? = null

    private var map: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLocationDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapViewBundle = savedInstanceState?.getBundle(MAP_VIEW_BUNDLE_KEY)
        binding.mapView.onCreate(mapViewBundle)

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        val jsonCharacterItem = arguments?.getString("cityLocationItem")
        cityLocationItem = Gson().fromJson(jsonCharacterItem, CityLocationItem::class.java)
        cityLocationItem?.let {
            binding.tvCity.text = it.name
            binding.tvState.text = it.state
            binding.tvLat.text = it.lat
            binding.tvLong.text = it.lon
        }

        binding.mapView.getMapAsync { googleMap ->
            // Save a reference to the GoogleMap
            map = googleMap

            // Configure and display the map
            val latLng = LatLng(cityLocationItem?.lat!!.toDouble(), cityLocationItem?.lon!!.toDouble())
            val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10f)
            googleMap.moveCamera(cameraUpdate)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        val mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY)
        binding.mapView?.onSaveInstanceState(mapViewBundle)
    }

    override fun onResume() {
        binding.mapView.onResume()
        super.onResume()
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }


}