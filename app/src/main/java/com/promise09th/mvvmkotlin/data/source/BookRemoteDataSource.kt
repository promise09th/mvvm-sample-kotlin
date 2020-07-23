package com.promise09th.mvvmkotlin.data.source

import com.promise09th.mvvmkotlin.presentation.model.BooksList
import com.promise09th.mvvmkotlin.remote.kakao.KakaoRetrofitService
import com.promise09th.mvvmkotlin.remote.mapper.RemoteMapper
import io.reactivex.Single
import javax.inject.Inject

class BookRemoteDataSource @Inject constructor(private val kakaoRetrofitService: KakaoRetrofitService) {

    fun getBooks(query: String, sort: String, page: Int, size: Int): Single<BooksList> {
        return kakaoRetrofitService.requestBooks(query, sort, page, size)
            .map { response ->
                BooksList(
                    RemoteMapper.mapToBookInfo(response.meta),
                    response.documents.map {
                        RemoteMapper.mapToBook(it)
                    })
            }
    }
}