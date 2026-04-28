package com.example.crypto_app_socket.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CoinDetailResponse(
    val success: Boolean,
    val coin: CoinModel
)