package com.example.citylocater.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.citylocater.api.CityLocationApi
import com.example.citylocater.models.CityLocationItem
import javax.inject.Inject

class CityLocationRepository  @Inject constructor(private val cityLocationApi: CityLocationApi){

    private val _cityLocationList = MutableLiveData<List<CityLocationItem>>()
    val cityLocationList : LiveData<List<CityLocationItem>>
        get() = _cityLocationList

    suspend fun getCityLocationList() {
        val result = cityLocationApi.getCityLocationAPI()
        if (result.isSuccessful && result.body() != null) {
            Log.d("_prabhat", "Database->${result.body()!!}")
            _cityLocationList.postValue(result.body())
        }
    }

}