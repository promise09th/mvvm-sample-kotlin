package com.promise09th.mvvmkotlin.data.mapper

import com.promise09th.mvvmkotlin.data.model.BookData
import com.promise09th.mvvmkotlin.data.model.BookInfoData
import com.promise09th.mvvmkotlin.db.model.BookEntity
import com.promise09th.mvvmkotlin.remote.model.BookDocument
import com.promise09th.mvvmkotlin.remote.model.BookMeta

fun BookMeta.mapToBookInfoData(): BookInfoData {
    return BookInfoData(
        total_count = this.total_count,
        pageable_count = this.pageable_count,
        is_end = this.is_end
    )
}

fun BookDocument.mapToBookData(): BookData {
    return BookData(
        title = this.title,
        contents = this.contents,
        url = this.url,
        isbn = this.isbn,
        datetime = this.datetime,
        authors = this.authors,
        publisher = this.publisher,
        translators = this.translators,
        price = this.price,
        sale_price = this.sale_price,
        thumbnail = this.thumbnail,
        status = this.status
    )
}

fun BookEntity.mapToBookData(): BookData {
    return BookData(
        title = this.title,
        contents = this.contents,
        url = this.url,
        isbn = this.isbn,
        datetime = this.datetime,
        authors = this.authors.split(",").toTypedArray(),
        publisher = this.publisher,
        translators = this.translators.split(",").toTypedArray(),
        price = this.price,
        sale_price = this.sale_price,
        thumbnail = this.thumbnail,
        status = this.status
    )
}

fun BookData.mapToBookEntity(): BookEntity {
    return BookEntity(
        title = this.title,
        contents = this.contents,
        url = this.url,
        isbn = this.isbn,
        datetime = this.datetime,
        authors = this.authors.joinToString(separator = ","),
        publisher = this.publisher,
        translators = this.translators.joinToString(separator = ","),
        price = this.price,
        sale_price = this.sale_price,
        thumbnail = this.thumbnail,
        status = this.status
    )
}
