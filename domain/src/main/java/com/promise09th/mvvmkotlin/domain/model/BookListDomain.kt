package com.promise09th.mvvmkotlin.domain.model

data class BookListDomain(
    val info: BookInfoDomain,
    val books: ArrayList<BookDomain>
)