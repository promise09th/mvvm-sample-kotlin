package com.promise09th.mvvmkotlin.presentation.model

data class BookInfoModel (
    val total_count: Int,
    val pageable_count: Int,
    val is_end: Boolean
)