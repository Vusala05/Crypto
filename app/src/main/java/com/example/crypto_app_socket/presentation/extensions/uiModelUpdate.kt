package com.example.crypto_app_socket.presentation.extensions

import com.example.crypto_app_socket.data.model.CoinUpdateModel
import com.example.crypto_app_socket.domain.uimodel.CoinUiModel

fun CoinUiModel.updateWith(update: CoinUpdateModel): CoinUiModel {
    return if (this.id == update.name) {
        this.copy(
            price = update.price,
            lastUpdated = update.timestamp
        )
    } else this
}