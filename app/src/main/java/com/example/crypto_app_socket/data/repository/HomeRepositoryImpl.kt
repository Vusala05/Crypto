package com.example.crypto_app_socket.data.repository

import com.example.crypto_app_socket.core.ContentState
import com.example.crypto_app_socket.core.constants.AppErrors
import com.example.crypto_app_socket.data.mappers.toUiModel
import com.example.crypto_app_socket.data.service.ApiService
import com.example.crypto_app_socket.domain.repository.HomeRepository
import com.example.crypto_app_socket.domain.uimodel.CoinUiModel
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
   val  apiService: ApiService
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
}