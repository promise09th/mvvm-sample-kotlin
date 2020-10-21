package com.promise09th.mvvmkotlin.db.mapper

import com.promise09th.mvvmkotlin.db.model.BookEntity
import com.promise09th.mvvmkotlin.presentation.model.Book

object EntityMapper {

    fun mapToBook(bookEntity: BookEntity) : Book {
        return Book(
            title = bookEntity.title,
            contents = bookEntity.contents,
            url = bookEntity.url,
            isbn = bookEntity.isbn,
            datetime = bookEntity.datetime,
            authors = bookEntity.authors.split(",").toTypedArray(),
            publisher = bookEntity.publisher,
            translators = bookEntity.translators.split(",").toTypedArray(),
            price = bookEntity.price,
            sale_price = bookEntity.sale_price,
            thumbnail = bookEntity.thumbnail,
            status = bookEntity.status
        )
    }

    fun mapToBookEntity(book: Book) : BookEntity {
        return BookEntity(
            title = book.title,
            contents = book.contents,
            url = book.url,
            isbn = book.isbn,
            datetime = book.datetime,
            authors = book.authors.joinToString(separator = ","),
            publisher = book.publisher,
            translators = book.translators.joinToString(separator = ","),
            price = book.price,
            sale_price = book.sale_price,
            thumbnail = book.thumbnail,
            status = book.status
        )
    }

}