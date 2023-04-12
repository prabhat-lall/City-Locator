package com.example.citylocater.api

import com.example.citylocater.models.CityLocationItem
import retrofit2.Response
import retrofit2.http.GET

interface CityLocationApi {

    @GET("dastagirkhan/00a6f6e32425e0944241/raw/33ca4e2b19695b2b93f490848314268ed5519894/gistfile1.json")
    suspend fun getCityLocationAPI():Response<List<CityLocationItem>>
}