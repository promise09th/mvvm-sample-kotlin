package com.promise09th.mvvmkotlin.presentation.model

data class BookModel(
    val title: String,
    val contents: String,
    val url: String,
    val isbn: String,
    val datetime: String,
    val authors: Array<String>,
    val publisher: String,
    val translators: Array<String>,
    val price: Int,
    val sale_price: Int,
    val thumbnail: String,
    val status: String
)