package com.promise09th.mvvmkotlin.remote.mapper

import com.promise09th.mvvmkotlin.presentation.model.Book
import com.promise09th.mvvmkotlin.presentation.model.BookInfo
import com.promise09th.mvvmkotlin.remote.model.BookDocument
import com.promise09th.mvvmkotlin.remote.model.BookMeta

object RemoteMapper {

    fun mapToBookInfo(bookMeta: BookMeta) : BookInfo{
        return BookInfo(
            total_count = bookMeta.total_count,
            pageable_count = bookMeta.pageable_count,
            is_end = bookMeta.is_end
        )
    }

    fun mapToBook(bookDocument: BookDocument): Book {
        return Book(
            title = bookDocument.title,
            contents = bookDocument.contents,
            url = bookDocument.url,
            isbn = bookDocument.isbn,
            datetime = bookDocument.datetime,
            authors = bookDocument.authors,
            publisher = bookDocument.publisher,
            translators = bookDocument.translators,
            price = bookDocument.price,
            sale_price = bookDocument.sale_price,
            thumbnail = bookDocument.thumbnail,
            status = bookDocument.status
        )
    }
}