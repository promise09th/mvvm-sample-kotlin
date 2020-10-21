package com.promise09th.mvvmkotlin.data.source

import com.promise09th.mvvmkotlin.db.database.AppDatabase
import com.promise09th.mvvmkotlin.db.mapper.EntityMapper
import com.promise09th.mvvmkotlin.presentation.model.Book
import com.promise09th.mvvmkotlin.presentation.model.BooksList
import com.promise09th.mvvmkotlin.remote.kakao.KakaoRetrofitService
import com.promise09th.mvvmkotlin.remote.mapper.RemoteMapper
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BookLocalDataSource @Inject constructor(private val appDatabase: AppDatabase) {

    fun getAllBooks(): Flowable<ArrayList<Book>> {
        return appDatabase.bookDao().getAll()
                .map { list -> list.asSequence()
                    .map { book -> EntityMapper.mapToBook(book) }
                    .toCollection(ArrayList())}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun insertBook(book: Book): Completable {
        return appDatabase.bookDao().insert(EntityMapper.mapToBookEntity(book))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteBook(book: Book): Completable {
        return appDatabase.bookDao().delete(EntityMapper.mapToBookEntity(book))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}