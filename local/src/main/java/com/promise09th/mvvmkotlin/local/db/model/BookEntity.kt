package com.promise09th.mvvmkotlin.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookEntity(
    @PrimaryKey val title: String,
    @ColumnInfo(name = "contents") val contents: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "isbn") val isbn: String,
    @ColumnInfo(name = "datetime") val datetime: String,
    @ColumnInfo(name = "authors") val authors: String,
    @ColumnInfo(name = "publisher") val publisher: String,
    @ColumnInfo(name = "translators") val translators: String,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "sale_price") val sale_price: Int,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "status") val status: String
)