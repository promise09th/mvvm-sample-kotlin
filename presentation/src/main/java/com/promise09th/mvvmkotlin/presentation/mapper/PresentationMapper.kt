package com.promise09th.mvvmkotlin.presentation.mapper

import com.promise09th.mvvmkotlin.domain.model.BookDomain
import com.promise09th.mvvmkotlin.domain.model.BookInfoDomain
import com.promise09th.mvvmkotlin.presentation.model.BookInfoModel
import com.promise09th.mvvmkotlin.presentation.model.BookModel

fun BookInfoDomain.mapToBookInfoModel(): BookInfoModel {
    return BookInfoModel(
        total_count = this.total_count,
        pageable_count = this.pageable_count,
        is_end = this.is_end
    )
}

fun BookInfoModel.mapToBookInfoDomain(): BookInfoDomain {
    return BookInfoDomain(
        total_count = this.total_count,
        pageable_count = this.pageable_count,
        is_end = this.is_end
    )
}

fun BookDomain.mapToBookModel(): BookModel {
    return BookModel(
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

fun BookModel.mapToBookDomain(): BookDomain {
    return BookDomain(
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

