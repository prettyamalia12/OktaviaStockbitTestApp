package com.stockbit.hiring.model

data class Data(var coinInfo: CoinInfo, var raw: RAW)

data class CoinInfo(var Name:String, var FullName:String)

data class RAW(var usd: USD)

data class USD(var PRICE:Double, var OPENHOUR: Double)