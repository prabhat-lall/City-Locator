package com.example.citylocater.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.citylocater.databinding.ItemCityLocationBinding
import com.example.citylocater.models.CityLocationItem

class FavListAdapter(
    private val onCityLocationItemClicked: (CityLocationItem) ->Unit,
    private val context: Context,
    private val addCityLocationList: List<CityLocationItem>

) : RecyclerView.Adapter<FavListAdapter.CityLocationViewHolder>() {

    private lateinit var binding: ItemCityLocationBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityLocationViewHolder {
        binding = ItemCityLocationBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CityLocationViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return addCityLocationList.size
    }

    override fun onBindViewHolder(holder: CityLocationViewHolder, position: Int) {
        val item = addCityLocationList[position]

        binding.tvCity.text = item.name
        binding.tvState.text = item.state

        binding.root.setOnClickListener {
            onCityLocationItemClicked(item)
        }

    }


    class CityLocationViewHolder(view : ItemCityLocationBinding) : RecyclerView.ViewHolder(view.root) {
    }


}