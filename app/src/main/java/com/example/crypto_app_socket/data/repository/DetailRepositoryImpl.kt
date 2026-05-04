package com.example.crypto_app_socket.data.repository

import com.example.crypto_app_socket.core.ContentState
import com.example.crypto_app_socket.core.constants.AppErrors
import com.example.crypto_app_socket.data.mappers.toUiModel
import com.example.crypto_app_socket.data.service.ApiService
import com.example.crypto_app_socket.domain.repository.DetailRepository
import com.example.crypto_app_socket.domain.uimodel.CoinUiModel
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor
    (val apiService: ApiService ) : DetailRepository  {
    override suspend fun getDetailRepository(id: String): ContentState<CoinUiModel> {
        return try {
            val res = apiService.getCoinDetail(id = id)
            val result = res.coin

                ContentState.Success(data = result.toUiModel())

        } catch (e: Exception) {
            ContentState.Error(e.message ?: AppErrors.unknownError)
        }
    }

}