package com.promise09th.mvvmkotlin.db.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.promise09th.mvvmkotlin.db.model.BookEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface BookDao {
    @Query("SELECT * FROM BookEntity")
    fun getAll(): Flowable<List<BookEntity>>

    @Insert(onConflict = REPLACE)
    fun insert(entity: BookEntity): Completable

    @Update
    fun update(entity: BookEntity): Completable

    @Delete
    fun delete(entity: BookEntity): Completable
}