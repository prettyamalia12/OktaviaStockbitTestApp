package com.stockbit.hiring

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stockbit.hiring.adapter.WatchlistAdapter
import com.stockbit.hiring.di.APIService
import com.stockbit.hiring.di.ServiceBuilder
import com.stockbit.hiring.model.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WatchlistActivity : AppCompatActivity() {

    lateinit var rvWatchlist : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watchlist)

        getWatchlist()
    }

    fun getWatchlist(){
        //initiate the service
        val apiService = ServiceBuilder.buildService(APIService::class.java)
        val requestCall = apiService.getCrypto(50, "USD")
        val stringUtils = StringUtils()
        //make network call asynchronously
        requestCall.enqueue(object : Callback<List<Data>> {
            override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
                if (response.isSuccessful) {
                    rvWatchlist.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(this@WatchlistActivity, 2)
                        adapter = WatchlistAdapter(response.body()!!)
                    }
                } else{
                        stringUtils.setMessage(this@WatchlistActivity, "Something went wrong")
                    }
                }

            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                stringUtils.setMessage(this@WatchlistActivity, "Something went wrong")
            }
        })
    }
}