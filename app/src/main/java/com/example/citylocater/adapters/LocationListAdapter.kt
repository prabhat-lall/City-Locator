package com.example.citylocater.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.citylocater.databinding.ItemCityLocationBinding
import com.example.citylocater.models.CityLocationItem

class LocationListAdapter(
    private val onCityLocationItemClicked: (CityLocationItem) ->Unit,
    private val context: Context,
    private val cityLocationList: List<CityLocationItem>

) : RecyclerView.Adapter<LocationListAdapter.CityLocationViewHolder>() {

    private lateinit var binding: ItemCityLocationBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityLocationViewHolder {
        binding = ItemCityLocationBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CityLocationViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cityLocationList.size
    }

    override fun onBindViewHolder(holder: CityLocationViewHolder, position: Int) {
        val item = cityLocationList[position]

        binding.tvCity.text = item.name
        binding.tvState.text = item.state

        binding.root.setOnClickListener {
            onCityLocationItemClicked(item)
        }

    }


    class CityLocationViewHolder(view : ItemCityLocationBinding) : RecyclerView.ViewHolder(view.root) {
    }


}