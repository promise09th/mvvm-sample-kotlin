package com.promise09th.mvvmkotlin.data.repository

import com.promise09th.mvvmkotlin.data.source.BookRemoteDataSource
import com.promise09th.mvvmkotlin.presentation.model.BooksList
import io.reactivex.Single
import javax.inject.Inject

class BookRepository @Inject constructor(private val bookRemoteDataSource: BookRemoteDataSource) {

    fun getBooks(query: String, sort: String, page: Int, size: Int): Single<BooksList> {
        return bookRemoteDataSource.getBooks(query, sort, page, size)
    }
}