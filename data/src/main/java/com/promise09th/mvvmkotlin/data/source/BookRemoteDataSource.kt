package com.promise09th.mvvmkotlin.data.source

import com.promise09th.mvvmkotlin.data.mapper.mapToBookData
import com.promise09th.mvvmkotlin.data.mapper.mapToBookInfoData
import com.promise09th.mvvmkotlin.data.model.BooksListData
import com.promise09th.mvvmkotlin.remote.kakao.KakaoRetrofitService
import io.reactivex.Single
import javax.inject.Inject

class BookRemoteDataSource @Inject constructor(private val kakaoRetrofitService: KakaoRetrofitService) {

    fun getBooks(query: String, sort: String, page: Int, size: Int): Single<BooksListData> {
        return kakaoRetrofitService.requestBooks(query, sort, page, size)
            .map { response ->
                BooksListData(
                    response.meta.mapToBookInfoData(),
                    ArrayList(response.documents.map { it.mapToBookData() })
                )
            }
    }
}