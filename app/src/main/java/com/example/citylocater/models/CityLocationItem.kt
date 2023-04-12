package com.example.citylocater.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.citylocater.utility.Constants.CITY_LOCATION_TABLE

@Entity(tableName = CITY_LOCATION_TABLE)
data class CityLocationItem(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val lat: String,
    val lon: String,
    val name: String,
    val state: String
)