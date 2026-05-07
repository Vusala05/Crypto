package com.example.crypto_app_socket.data.repository

import com.example.crypto_app_socket.core.ContentState
import com.example.crypto_app_socket.core.constants.AppErrors
import com.example.crypto_app_socket.data.mappers.toUiModel
import com.example.crypto_app_socket.data.service.ApiService
import com.example.crypto_app_socket.data.socket.SocketService
import com.example.crypto_app_socket.domain.repository.DetailRepository
import com.example.crypto_app_socket.domain.uimodel.CoinUiModel
import com.example.crypto_app_socket.domain.uimodel.CoinUpdateUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    val apiService: ApiService,
    val socketService: SocketService) : DetailRepository  {
    override suspend fun getDetailRepository(id: String): ContentState<CoinUiModel> {
        return try {
            val res = apiService.getCoinDetail(id = id)
            val result = res.coin
            ContentState.Success(data = result.toUiModel())

        } catch (e: Exception) {
            ContentState.Error(e.message ?: AppErrors.unknownError)
        }
    }

    override fun subscribeDetailCoin(id: String) {
        socketService.subscribeDetail(id = id)
    }

    override fun observeCoinUpdate(event : String) : Flow<CoinUpdateUiModel> {
        return socketService.observeUpdates(event = event).map {
            it.toUiModel()
        }
    }

}