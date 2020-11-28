package com.promise09th.mvvmkotlin.remote.kakao

import com.promise09th.mvvmkotlin.remote.BuildConfig

object KakaoApiKey {
    const val REST_API_KEY: String = BuildConfig.KAKAO_REST_API_KEY
    const val REST_API_HEADER = "Authorization: KakaoAK $REST_API_KEY"
}