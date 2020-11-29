package com.promise09th.mvvmkotlin.domain.usecase.book

import com.promise09th.mvvmkotlin.data.repository.BookRepository
import com.promise09th.mvvmkotlin.domain.mapper.mapToBookDomain
import com.promise09th.mvvmkotlin.domain.mapper.mapToBookInfoDomain
import com.promise09th.mvvmkotlin.domain.model.BookListDomain
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(private val bookRepository: BookRepository) {

    fun create(query: String, page: Int = 1): Single<BookListDomain> {
        return bookRepository.getBooks(query = query, sort = ACCURACY_SORT, page = page, size = DOCUMENT_SIZE)
            .map {
                BookListDomain(
                    it.info.mapToBookInfoDomain(),
                    ArrayList(it.books.map { it.mapToBookDomain() })
                )
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    companion object {
        const val DOCUMENT_SIZE = 50
        const val ACCURACY_SORT = "accuracy"
    }
}
