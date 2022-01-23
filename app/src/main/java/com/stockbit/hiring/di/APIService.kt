package com.stockbit.hiring.di

import com.stockbit.hiring.R
import com.stockbit.hiring.model.Data
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIService {
    @Headers("api-key: " + R.string.API_KEY)
    @GET("data/top/totaltoptiervolfull")
    fun getCrypto(@Query("limit") limit: Int, @Query("tysm") tsym: String): Call<List<Data>>

}

interface APIHelper {
    suspend fun getCrypto(): Response<List<Data>>
}