package com.example.crypto_app_socket.domain.uimodel

import com.example.crypto_app_socket.core.constants.StatusState

data class CoinUpdateUiModel(
    val name: String,
    val price: Double,
    val timestamp: String,
    val isUp : Boolean?,
    val percentChange : Double?,
)