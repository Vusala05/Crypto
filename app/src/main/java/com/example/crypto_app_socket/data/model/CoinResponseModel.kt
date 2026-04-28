package com.example.crypto_app_socket.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CoinResponseModel(
    val success: Boolean,
    val coins: List<CoinModel>
)
