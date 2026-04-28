package com.example.crypto_app_socket.domain.usecases

import com.example.crypto_app_socket.core.ContentState
import com.example.crypto_app_socket.domain.repository.HomeRepository
import com.example.crypto_app_socket.domain.uimodel.CoinUiModel
import javax.inject.Inject

class FetchAllCoinsUseCase @Inject constructor(val homeRepository: HomeRepository)  {

    suspend operator fun invoke() : ContentState<List<CoinUiModel>>{
        return homeRepository.getAllCoins()
    }

}