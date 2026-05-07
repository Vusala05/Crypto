package com.example.crypto_app_socket.domain.repository

import com.example.crypto_app_socket.core.ContentState
import com.example.crypto_app_socket.domain.uimodel.CoinUiModel
import com.example.crypto_app_socket.domain.uimodel.CoinUpdateUiModel
import kotlinx.coroutines.flow.Flow

interface DetailRepository {

    suspend fun getDetailRepository(id : String): ContentState<CoinUiModel>

    suspend fun subscribeDetailCoin(id : String)

    fun observeCoinUpdate (event : String) : Flow<CoinUpdateUiModel>
}