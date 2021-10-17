package com.summerdewyes.mvvm_check_bang.api


import com.summerdewyes.mvvm_check_bang.BuildConfig.X_NAVER_CLINET_ID
import com.summerdewyes.mvvm_check_bang.BuildConfig.X_NAVER_CLINET_SECRET
import com.summerdewyes.mvvm_check_bang.models.BookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NaverAPI {

    @Headers(
        "X-Naver-Client-Id: " + X_NAVER_CLINET_ID,
        "X-Naver-Client-Secret: " + X_NAVER_CLINET_SECRET
    )

    @GET("v1/search/book.json")
    suspend fun searchForBook(
        @Query("query")
        query: String,
        @Query("display")
        display: Int = 10
    ): Response<BookResponse>

}