package com.promise09th.mvvmkotlin.domain.model

data class BookInfoDomain(
    val total_count: Int,
    val pageable_count: Int,
    val is_end: Boolean
)