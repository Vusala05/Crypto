package com.example.crypto_app_socket.domain.uimodel

data class CoinUiModel(
    val id: String,
    val shortName: String,
    val longName: String,
    val image: String,
    override val price: Double?,
    override val percentChange: Double?,
    override val isUp: Boolean?,
) : ComingUpdatedData {
    companion object {
        val empty = CoinUiModel(
            id = "",
            shortName = "",
            longName = "",
            image = "",
            price = 0.0,
            percentChange = 0.0,
            isUp = true,
        )

    }
}