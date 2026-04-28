package com.example.crypto_app_socket.data.mappers

import com.example.crypto_app_socket.core.util.percentFormat
import com.example.crypto_app_socket.core.util.priceFormat
import com.example.crypto_app_socket.data.model.CoinModel
import com.example.crypto_app_socket.domain.uimodel.CoinUiModel

fun CoinModel.toUiModel() : CoinUiModel = CoinUiModel(
    id =  id ,
    shortName = shortName ?:"",
    longName = longName ?:"",
    image = image ?:"",
    price = price ?: 0.0 ,
    changePercent = changePercent ?: 0.0,
    isUp = isUp ?: false
)


