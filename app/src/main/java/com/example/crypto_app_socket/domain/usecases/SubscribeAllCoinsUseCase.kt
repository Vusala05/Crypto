package com.example.crypto_app_socket.domain.usecases

import com.example.crypto_app_socket.domain.repository.HomeRepository
import javax.inject.Inject

class SubscribeAllCoinsUseCase @Inject constructor(val repository: HomeRepository) {

    suspend operator fun invoke(coins: List<String>) {
            return repository.subscribeCrypto(coins)
        }
}