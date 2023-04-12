package com.example.citylocater.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.citylocater.api.CityLocationApi
import com.example.citylocater.db.CityLocationDatabase
import com.example.citylocater.models.CityLocationItem
import com.example.citylocater.utility.NetworkUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CityLocationRepository @Inject constructor(
    private val cityLocationApi: CityLocationApi,
    private val db: CityLocationDatabase,
    @ApplicationContext private val context: Context
) {

    private val _cityLocationList = MutableLiveData<List<CityLocationItem>>()
    val cityLocationList: LiveData<List<CityLocationItem>>
        get() = _cityLocationList

    suspend fun getCityLocationList() {
        if (NetworkUtils.isInternetAvailable(context)) {
            val result = cityLocationApi.getCityLocationAPI()
            if (result.isSuccessful && result.body() != null) {
                db.cityLocationDao().insertCityLocationList(result.body()!!)
                Log.d("_prabhat", "Database->${result.body()!!}")
                _cityLocationList.postValue(result.body())
            }
        }else{
            val list = db.cityLocationDao().getCityLocationList()
            _cityLocationList.postValue(list)
        }
    }

}