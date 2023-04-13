package com.example.citylocater.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.citylocater.models.CityLocationItem


@Dao
interface CityLocationDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun insertCityLocationList(cityLocationItem: List<CityLocationItem>)

    @Query("SELECT * FROM CITY_LOCATION_TABLE")
    fun getCityLocationList(): List<CityLocationItem>




}