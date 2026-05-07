package com.example.crypto_app_socket.domain.usecases

import com.example.crypto_app_socket.domain.repository.DetailRepository
import com.example.crypto_app_socket.domain.uimodel.CoinUpdateUiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

    class ObserveDetailUpdateUseCase @Inject constructor(val repository: DetailRepository) {
        operator fun invoke() : Flow<CoinUpdateUiModel>{
            return repository.observeCoinUpdate("cryptoDetailUpdate")
        }
    }
