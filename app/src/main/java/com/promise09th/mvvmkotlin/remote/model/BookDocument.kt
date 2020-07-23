package com.promise09th.mvvmkotlin.remote.model

data class BookDocument(
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
