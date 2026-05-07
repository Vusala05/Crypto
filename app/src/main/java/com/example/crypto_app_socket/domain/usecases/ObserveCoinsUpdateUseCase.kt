package com.example.crypto_app_socket.domain.usecases

import com.example.crypto_app_socket.domain.repository.HomeRepository
import com.example.crypto_app_socket.domain.uimodel.CoinUpdateUiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveCoinsUpdateUseCase @Inject constructor(val repository: HomeRepository) {
    suspend operator fun invoke() : Flow<CoinUpdateUiModel>{
        return repository.observeCrypto("cryptoUpdate")
    }
}