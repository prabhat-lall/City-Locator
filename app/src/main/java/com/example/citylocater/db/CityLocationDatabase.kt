package com.example.citylocater.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.citylocater.models.CityLocationItem


@Database(entities = [CityLocationItem::class], version = 1, exportSchema = false)
abstract class CityLocationDatabase : RoomDatabase(){
    abstract  fun cityLocationDao():CityLocationDAO
}