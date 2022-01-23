package com.stockbit.hiring

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stockbit.hiring.adapter.WatchlistAdapter
import com.stockbit.hiring.di.APIService
import com.stockbit.hiring.di.ServiceBuilder
import com.stockbit.hiring.model.Object
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WatchlistActivity : AppCompatActivity() {

    lateinit var rvWatchlist : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watchlist)

        rvWatchlist = findViewById(R.id.rvWatchlist)

        getWatchlist()
    }

    private fun getWatchlist(){
        //initiate the service
        val apiService = ServiceBuilder.buildService(APIService::class.java)
        val requestCall = apiService.getCrypto(50, "USD", getString(R.string.API_KEY))
        //make network call asynchronously
        requestCall.enqueue(object : Callback<Object> {
            override fun onResponse(call: Call<Object>, response: Response<Object>) {
                if (response.isSuccessful) {
                    rvWatchlist.apply {
                        if (response.body()!=null){
                            setHasFixedSize(true)
                            layoutManager = GridLayoutManager(this@WatchlistActivity, 1)
                            adapter = WatchlistAdapter(response.body()!!)
                        }else{
                            setErrorMessage()
                        }

                    }
                } else{
                        setErrorMessage()
                    }
                }

            override fun onFailure(call: Call<Object>, t: Throwable) {
                setErrorMessage()
            }
        })
    }

    fun setErrorMessage(){
        val stringUtils = StringUtils()
        stringUtils.setMessage(this@WatchlistActivity, "Something went wrong")
    }
}