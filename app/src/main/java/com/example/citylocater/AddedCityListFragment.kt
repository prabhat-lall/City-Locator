package com.example.citylocater

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.citylocater.adapters.FavListAdapter
import com.example.citylocater.databinding.FragmentAddedCityListBinding
import com.example.citylocater.databinding.FragmentLocationDetailBinding
import com.example.citylocater.models.CityLocationItem
import com.google.gson.Gson


class AddedCityListFragment : Fragment() {

    lateinit var binding: FragmentAddedCityListBinding
    lateinit var adapter: FavListAdapter
    private var cityLocationItem: CityLocationItem? = null
    val cityLocationList: MutableList<CityLocationItem> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddedCityListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fbAdd.setOnClickListener {
            val city = binding.tvCityName.text.toString()
            val state = binding.tvState.text.toString()
            val lat = binding.tvLat.text.toString()
            val lon = binding.tvLon.text.toString()
            val data = CityLocationItem(lat = lat, lon = lon, name = city, state = state)
            if (data.lat.isNullOrEmpty() || !data.lon.isNullOrEmpty() || !data.name.isNullOrEmpty() || !data.state.isNullOrEmpty()) {
                cityLocationList.add(data)
                Toast.makeText(requireContext(), "Added ${data.name}", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Fill all Data", Toast.LENGTH_SHORT).show()
            }

        }

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.rvAddCity.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAddCity.adapter =
            FavListAdapter(::onCityLocationItemClicked, requireContext(), cityLocationList)

    }

    private fun onCityLocationItemClicked(cityLocationItem: CityLocationItem) {
        val bundle = Bundle()
        bundle.putString("cityLocationItem", Gson().toJson(cityLocationItem))
        findNavController().navigate(
            R.id.action_addedCityListFragment_to_locationDetailFragment,
            bundle
        )
        Toast.makeText(requireContext(), "passing data to detail fragment", Toast.LENGTH_SHORT)
            .show()
    }

}