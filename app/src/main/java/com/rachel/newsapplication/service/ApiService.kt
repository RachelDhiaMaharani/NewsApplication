package com.rachel.newsapplication.service

import com.rachel.newsapplication.model.ResponseNews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    fun getNewsHeadlines(
        @Query("country") country: String?,
        @Query("apyKey") apiKey : String?
    ): Call<ResponseNews>
}