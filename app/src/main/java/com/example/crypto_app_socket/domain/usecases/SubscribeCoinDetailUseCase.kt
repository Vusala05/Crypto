package com.example.crypto_app_socket.domain.usecases

import com.example.crypto_app_socket.domain.repository.DetailRepository
import javax.inject.Inject

    class SubscribeCoinDetailUseCase @Inject constructor(val repository: DetailRepository) {

       suspend operator fun invoke(id : String){
            return repository.subscribeDetailCoin(id)
        }
    }
