package com.example.citylocater.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.citylocater.R
import com.example.citylocater.adapters.LocationListAdapter
import com.example.citylocater.databinding.FragmentHomeBinding
import com.example.citylocater.models.CityLocationItem
import com.example.citylocater.ui.viewmodel.MainViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var viewModel : MainViewModel
    private lateinit var adapter: LocationListAdapter
    private lateinit var binding : FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.locationLiveData.observe(viewLifecycleOwner) {
            binding.rvCityLocation.layoutManager = LinearLayoutManager(requireContext())
            adapter = LocationListAdapter(::onCityLocationItemClicked,requireContext(), it)
            binding.rvCityLocation.adapter = adapter

        }

        binding.fbAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addedCityListFragment)
        }

    }

    private fun onCityLocationItemClicked(cityLocationItem:CityLocationItem){
        val bundle = Bundle()
        bundle.putString("cityLocationItem", Gson().toJson(cityLocationItem))
        findNavController().navigate(R.id.action_homeFragment_to_locationDetailFragment,bundle)
    }

}