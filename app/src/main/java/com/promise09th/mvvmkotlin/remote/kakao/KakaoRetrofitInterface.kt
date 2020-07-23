package com.promise09th.mvvmkotlin.remote.kakao

import com.promise09th.mvvmkotlin.remote.model.BookResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoRetrofitService {

    @Headers(KakaoApiKey.REST_API_HEADER)
    @GET("/v3/search/book")
    fun requestBooks(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Single<BookResponse>
}
