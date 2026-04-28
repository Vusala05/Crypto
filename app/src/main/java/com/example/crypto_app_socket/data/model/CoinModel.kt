package com.example.crypto_app_socket.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CoinModel(
    val id: String,
    val shortName: String?=null,
    val longName: String?=null,
    val image: String?=null,
    val price: Double?=null,
    val changePercent: Double?=null,
    val isUp: Boolean?=null
)

