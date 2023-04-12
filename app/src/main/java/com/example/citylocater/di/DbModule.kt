package com.example.citylocater.di

import android.content.Context
import androidx.room.Room
import com.example.citylocater.db.CityLocationDatabase
import com.example.citylocater.models.CityLocationItem
import com.example.citylocater.utility.Constants.CITY_LOCATION_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    //Hilt needs to know how to create an instance of NoteDatabase. For that add another method below provideDao.
    @Provides
    @Singleton
    fun provide(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, CityLocationDatabase::class.java, CITY_LOCATION_DATABASE)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
    //This annotation marks the method provideDao as a provider of noteDoa.
    @Provides
    @Singleton
    fun provideDao(db: CityLocationDatabase) = db.cityLocationDao()

}