package com.example.crypto_app_socket.domain.repository

import com.example.crypto_app_socket.core.ContentState
import com.example.crypto_app_socket.domain.uimodel.CoinUiModel

interface HomeRepository {

    suspend fun getAllCoins () : ContentState<List<CoinUiModel>>
}