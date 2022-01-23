package com.stockbit.hiring

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.stockbit.hiring.adapter.WatchlistAdapter
import com.stockbit.hiring.di.APIService
import com.stockbit.hiring.di.ServiceBuilder
import com.stockbit.hiring.model.Object
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WatchlistActivity : AppCompatActivity() {

    lateinit var rvWatchlist : RecyclerView
    lateinit var swipeRefresh : SwipeRefreshLayout

    private val refreshListener = SwipeRefreshLayout.OnRefreshListener {
        swipeRefresh.isRefreshing = true
        // call api to reload the screen
        getWatchlist()
        //set the refresh animation to false again
        swipeRefresh.isRefreshing = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watchlist)

        rvWatchlist = findViewById(R.id.rvWatchlist)
        swipeRefresh = findViewById(R.id.swipe_container)
        swipeRefresh.setOnRefreshListener(refreshListener);

        setHeaderLayout()
        getWatchlist()

    }

    private fun setHeaderLayout() {
        findViewById<ImageView>(R.id.imgLeftHeader)
            .setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_list))

        findViewById<ImageView>(R.id.imgRightHeader)
            .setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_paper_note))

//        hide title and show the logo
        findViewById<TextView>(R.id.txtHeader).visibility = View.GONE
        findViewById<ImageView>(R.id.imgLogo).visibility = View.VISIBLE
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
                        if (response.body() != null){
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