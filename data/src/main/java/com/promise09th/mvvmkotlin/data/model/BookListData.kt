package com.promise09th.mvvmkotlin.data.model

data class BooksListData(
    val info: BookInfoData,
    val books: ArrayList<BookData>
)