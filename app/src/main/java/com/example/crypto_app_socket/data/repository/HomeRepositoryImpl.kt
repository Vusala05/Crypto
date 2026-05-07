package com.example.crypto_app_socket.data.repository

import com.example.crypto_app_socket.core.ContentState
import com.example.crypto_app_socket.core.constants.AppErrors
import com.example.crypto_app_socket.data.mappers.toUiModel
import com.example.crypto_app_socket.data.service.ApiService
import com.example.crypto_app_socket.data.socket.SocketService
import com.example.crypto_app_socket.domain.repository.HomeRepository
import com.example.crypto_app_socket.domain.uimodel.CoinUiModel
import com.example.crypto_app_socket.domain.uimodel.CoinUpdateUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
   val  apiService: ApiService,
    val socketService: SocketService
) : HomeRepository {
    override suspend fun getAllCoins(): ContentState<List<CoinUiModel>> {
        return try {
            val res = apiService.getAllCoins()
            val result = res.coins
            if (result.isEmpty()) {
                ContentState.Error(AppErrors.no_coins)
            } else {
                ContentState.Success(data = result.map { it.toUiModel() })
            }
        } catch (e: Exception) {
            ContentState.Error(e.message ?: AppErrors.unknownError)

        }

    }

    override suspend fun subscribeCrypto(coin: List<String>) {
        socketService.subscribeCryptoList(coin)
    }

    override suspend fun observeCrypto(event : String) : Flow<CoinUpdateUiModel> {
        return socketService.observeUpdates(event = event).map {
            it.toUiModel()
        }
    }
}