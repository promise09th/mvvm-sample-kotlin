package com.promise09th.mvvmkotlin.presentation.injection.module

import android.app.Application
import androidx.room.Room
import com.promise09th.mvvmkotlin.db.database.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DbModule {

    @Provides
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java, "Mvvm.db")
            .build()
    }
}