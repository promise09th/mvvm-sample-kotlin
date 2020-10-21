package com.promise09th.mvvmkotlin.data.repository

import com.promise09th.mvvmkotlin.data.source.BookLocalDataSource
import com.promise09th.mvvmkotlin.data.source.BookRemoteDataSource
import com.promise09th.mvvmkotlin.presentation.model.Book
import com.promise09th.mvvmkotlin.presentation.model.BooksList
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val bookRemoteDataSource: BookRemoteDataSource,
    private val bookLocalDataSource: BookLocalDataSource
) {
    fun getBooks(query: String, sort: String, page: Int, size: Int) =
        bookRemoteDataSource.getBooks(query, sort, page, size)

    fun getAllBooks() = bookLocalDataSource.getAllBooks()

    fun saveBook(book: Book) = bookLocalDataSource.insertBook(book)

    fun deleteBook(book: Book) = bookLocalDataSource.deleteBook(book)
}