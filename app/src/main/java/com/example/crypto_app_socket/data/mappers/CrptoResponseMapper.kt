package com.example.crypto_app_socket.data.mappers

import com.example.crypto_app_socket.data.model.CoinModel
import com.example.crypto_app_socket.data.model.CoinUpdateModel
import com.example.crypto_app_socket.domain.uimodel.CoinUiModel
import com.example.crypto_app_socket.domain.uimodel.CoinUpdateUiModel

fun CoinModel.toUiModel() : CoinUiModel = CoinUiModel(
    id =  id ,
    shortName = shortName ?:"",
    longName = longName ?:"",
    image = image ?:"",
    price = price ?: 0.0 ,
    percentChange = changePercent ?: 0.0,
    isUp = isUp ?: false,
)

fun CoinUpdateModel.toUiModel() : CoinUpdateUiModel = CoinUpdateUiModel(
    name = this.name,
    price = this.price,
    timestamp = this.timestamp,
    isUp = null,
    percentChange = null
)


