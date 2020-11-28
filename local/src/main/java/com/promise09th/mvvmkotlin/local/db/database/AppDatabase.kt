package com.promise09th.mvvmkotlin.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.promise09th.mvvmkotlin.db.dao.BookDao
import com.promise09th.mvvmkotlin.db.model.BookEntity

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDao
}