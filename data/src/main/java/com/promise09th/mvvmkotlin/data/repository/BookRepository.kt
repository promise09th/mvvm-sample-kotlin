package com.promise09th.mvvmkotlin.data.repository

import com.promise09th.mvvmkotlin.data.model.BookData
import com.promise09th.mvvmkotlin.data.source.BookLocalDataSource
import com.promise09th.mvvmkotlin.data.source.BookRemoteDataSource
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val bookRemoteDataSource: BookRemoteDataSource,
    private val bookLocalDataSource: BookLocalDataSource
) {
    fun getBooks(query: String, sort: String, page: Int, size: Int) =
        bookRemoteDataSource.getBooks(query, sort, page, size)

    fun getAllBooks() = bookLocalDataSource.getAllBooks()

    fun saveBook(bookData: BookData) = bookLocalDataSource.insertBook(bookData)

    fun deleteBook(bookData: BookData) = bookLocalDataSource.deleteBook(bookData)
}