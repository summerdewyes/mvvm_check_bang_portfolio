package com.summerdewyes.mvvm_check_bang.api


import com.summerdewyes.mvvm_check_bang.BookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NaverAPI {

    @Headers(
        "X-Naver-Client-Id: ",
        "X-Naver-Client-Secret: "
    )

    @GET("v1/search/book.json")
    suspend fun searchForBook(
        @Query("query")
        query: String,
        @Query("display")
        display: Int = 10,
        @Query("start")
        start: Int = 1
    ): Response<BookResponse>

}