package com.example.citylocater

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CityLocationLocator : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}