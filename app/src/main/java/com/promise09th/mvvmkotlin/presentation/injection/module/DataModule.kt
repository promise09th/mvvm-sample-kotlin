package com.promise09th.mvvmkotlin.presentation.injection.module

import com.promise09th.mvvmkotlin.data.repository.BookRepository
import com.promise09th.mvvmkotlin.data.source.BookRemoteDataSource
import com.promise09th.mvvmkotlin.remote.kakao.KakaoRetrofitService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideBookRepository(remote: BookRemoteDataSource): BookRepository {
        return BookRepository(remote)
    }

    @Singleton
    @Provides
    fun provideBookRemoteDataSource(kakaoRetrofitService: KakaoRetrofitService): BookRemoteDataSource {
        return BookRemoteDataSource(kakaoRetrofitService)
    }
}