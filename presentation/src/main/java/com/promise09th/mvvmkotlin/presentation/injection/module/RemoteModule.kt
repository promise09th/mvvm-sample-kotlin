package com.promise09th.mvvmkotlin.presentation.injection.module

import com.promise09th.mvvmkotlin.remote.RetrofitClient
import com.promise09th.mvvmkotlin.remote.kakao.KakaoRetrofitService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun provideKakaoRetrofitService(): KakaoRetrofitService {
        return RetrofitClient.kakaoClient.create(KakaoRetrofitService::class.java)
    }
}