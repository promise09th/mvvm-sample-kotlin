package com.promise09th.mvvmkotlin.remote.model

data class BookResponse(
    val meta: BookMeta,
    val documents: List<BookDocument>
)