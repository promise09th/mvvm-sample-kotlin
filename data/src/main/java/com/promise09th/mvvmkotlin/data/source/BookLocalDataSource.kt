package com.promise09th.mvvmkotlin.data.source

import com.promise09th.mvvmkotlin.data.mapper.mapToBookData
import com.promise09th.mvvmkotlin.data.mapper.mapToBookEntity
import com.promise09th.mvvmkotlin.data.model.BookData
import com.promise09th.mvvmkotlin.db.database.AppDatabase
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BookLocalDataSource @Inject constructor(private val appDatabase: AppDatabase) {

    fun getAllBooks(): Flowable<ArrayList<BookData>> {
        return appDatabase.bookDao().getAll()
            .map {
                it.asSequence()
                    .map { it.mapToBookData() }
                    .toCollection(ArrayList())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun insertBook(bookData: BookData): Completable {
        return appDatabase.bookDao().insert(bookData.mapToBookEntity())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteBook(bookData: BookData): Completable {
        return appDatabase.bookDao().delete(bookData.mapToBookEntity())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}