package com.example.crypto_app_socket.domain.uimodel

data class CoinUpdateUiModel(
    val name: String,
    override val price: Double,
    val timestamp: String,
    override val isUp : Boolean?,
    override val percentChange : Double?,
) : ComingUpdatedData