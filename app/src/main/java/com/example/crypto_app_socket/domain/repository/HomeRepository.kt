package com.example.crypto_app_socket.domain.repository

import com.example.crypto_app_socket.core.ContentState
import com.example.crypto_app_socket.domain.uimodel.CoinUiModel
import com.example.crypto_app_socket.domain.uimodel.CoinUpdateUiModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun getAllCoins () : ContentState<List<CoinUiModel>>

    suspend fun subscribeCrypto (coin : List<String>)

    suspend fun observeCrypto (event : String ) : Flow<CoinUpdateUiModel>

}