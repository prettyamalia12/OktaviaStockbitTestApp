package com.stockbit.hiring.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.stockbit.hiring.R
import com.stockbit.hiring.model.Data
import com.stockbit.hiring.model.Object
import java.math.RoundingMode

class WatchlistAdapter(private val resObj: Object) : RecyclerView.Adapter<WatchlistHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): WatchlistHolder {
        return WatchlistHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_watchlist, viewGroup, false))
    }

    override fun getItemCount(): Int = resObj.data.size

    override fun onBindViewHolder(holder: WatchlistHolder, position: Int) {
        context = holder.itemView.context
        holder.bindHero(context, resObj.data[position])
    }
}

class WatchlistHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val txtName = view.findViewById<TextView>(R.id.txtName)
    private val txtFullName = view.findViewById<TextView>(R.id.txtFullName)
    private val txtPrice = view.findViewById<TextView>(R.id.txtPrice)
    private val txtChanges = view.findViewById<TextView>(R.id.txtChanges)

    fun bindHero(context: Context, crypto: Data) {
        txtName.text = crypto.coinInfo.Name
        txtFullName.text = crypto.coinInfo.FullName

        var strPrice = ""
        var price = 0.0
        
        if (crypto.raw != null){
            price = crypto.raw?.usd?.price ?: 0.0
            price = price.toBigDecimal().setScale(4, RoundingMode.UP).toDouble()
            strPrice = "$$price"
        }
        txtPrice.text = strPrice

        var strChanges = ""
        var changes: Double

        if (crypto.raw != null){
            val openHour = crypto.raw?.usd?.openHour ?: 0.0
            changes = ((openHour-price)/100).toBigDecimal().setScale(4, RoundingMode.UP).toDouble()

            //check whether the changes is positive or negative
            if (changes > 0.0){
                strChanges =  "+$changes"
                //change text color to green
                txtChanges.setTextColor(ContextCompat.getColor(context, R.color.colorAccentDark))
            }else{
                strChanges = changes.toString()
                //change text color to red
                txtChanges.setTextColor(ContextCompat.getColor(context, R.color.colorErrorRed))
            }
        }
        txtChanges.text = strChanges
        
       
        
    }
}