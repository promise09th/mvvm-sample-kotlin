package com.promise09th.mvvmkotlin.domain.model

data class BookDomain(
    val title: String= "",
    val contents: String = "",
    val url: String = "",
    val isbn: String = "",
    val datetime: String = "",
    val authors: Array<String> = arrayOf(),
    val publisher: String = "",
    val translators: Array<String> = arrayOf(),
    val price: Int = 0,
    val sale_price: Int = 0,
    val thumbnail: String = "",
    val status: String = ""
)
