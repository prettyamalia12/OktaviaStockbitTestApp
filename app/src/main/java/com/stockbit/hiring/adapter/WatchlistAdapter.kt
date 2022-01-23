package com.stockbit.hiring.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stockbit.hiring.R
import com.stockbit.hiring.model.Data

class WatchlistAdapter(private val crypto: List<Data>) : RecyclerView.Adapter<WatchlistHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): WatchlistHolder {
        return WatchlistHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_watchlist, viewGroup, false))
    }

    override fun getItemCount(): Int = crypto.size

    override fun onBindViewHolder(holder: WatchlistHolder, position: Int) {
        holder.bindHero(crypto[position])
    }
}

class WatchlistHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val txtName = view.findViewById<TextView>(R.id.txtName)
    private val txtFullName = view.findViewById<TextView>(R.id.txtFullName)
    private val txtPrice = view.findViewById<TextView>(R.id.txtPrice)
    private val txtChanges = view.findViewById<TextView>(R.id.txtChanges)

    fun bindHero(crypto: Data) {
        txtName.text = crypto.coinInfo.Name
        txtFullName.text = crypto.coinInfo.FullName

        val price = crypto.raw.usd.PRICE
        val openHour = crypto.raw.usd.OPENHOUR

        txtPrice.text = "$$price"
        txtChanges.text = ((openHour-price)/100).toString()
    }
}