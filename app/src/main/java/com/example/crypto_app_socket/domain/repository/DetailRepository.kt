package com.example.crypto_app_socket.domain.repository

import com.example.crypto_app_socket.core.ContentState
import com.example.crypto_app_socket.domain.uimodel.CoinUiModel

interface DetailRepository {

    suspend fun getDetailRepository(id : String): ContentState<CoinUiModel>
}