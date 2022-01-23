package com.stockbit.hiring.model

import com.google.gson.annotations.SerializedName

data class Object(
    @SerializedName("Data")
    var data : List<Data>)

data class Data(
    @SerializedName("CoinInfo")
    var coinInfo : CoinInfo,
    @SerializedName("RAW")
    var raw : RAW?)

data class CoinInfo(
    @SerializedName("Name")
    var Name :String,
    @SerializedName("FullName")
    var FullName :String)

data class RAW(
    @SerializedName("USD")
    var usd : USD)

data class USD(
    @SerializedName("PRICE")
    var price : Double,
    @SerializedName("OPENHOUR")
    var openHour : Double)