package com.promise09th.mvvmkotlin.domain.book

import com.promise09th.mvvmkotlin.data.repository.BookRepository
import com.promise09th.mvvmkotlin.domain.UseCase
import com.promise09th.mvvmkotlin.presentation.model.BooksList
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(private val bookRepository: BookRepository): UseCase {

    fun execute(query: String, page: Int): Single<BooksList> {
        return bookRepository.getBooks(query = query, sort = ACCURACY_SORT, page = page, size = DOCUMENT_SIZE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    companion object {
        const val DOCUMENT_SIZE = 50
        const val ACCURACY_SORT = "accuracy"
    }
}
