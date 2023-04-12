package com.example.citylocater.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citylocater.models.CityLocationItem
import com.example.citylocater.repository.CityLocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: CityLocationRepository) : ViewModel() {

    val locationLiveData: LiveData<List<CityLocationItem>>
        get() = repository.cityLocationList

    init {
        viewModelScope.launch {
            repository.getCityLocationList()
        }
    }

}