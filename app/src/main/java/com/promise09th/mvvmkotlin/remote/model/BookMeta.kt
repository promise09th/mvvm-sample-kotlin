package com.promise09th.mvvmkotlin.remote.model

data class BookMeta(
    val total_count: Int,
    val pageable_count: Int,
    val is_end: Boolean
)

