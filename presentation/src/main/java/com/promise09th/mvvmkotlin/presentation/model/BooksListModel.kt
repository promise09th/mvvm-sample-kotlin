package com.promise09th.mvvmkotlin.presentation.model

data class BooksListModel(
    val info: BookInfoModel,
    val books: List<BookModel>
)