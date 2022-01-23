package com.stockbit.hiring.di

import com.stockbit.hiring.model.Object
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("data/top/totaltoptiervolfull")
    fun getCrypto(@Query("limit") limit: Int, @Query("tsym") tsym: String, @Query("api_key") apiKey:String): Call<Object>
}
