package com.promise09th.mvvmkotlin.data.model

data class BookData(
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