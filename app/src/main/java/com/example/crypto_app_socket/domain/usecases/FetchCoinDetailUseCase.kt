package com.example.crypto_app_socket.domain.usecases

import com.example.crypto_app_socket.core.ContentState
import com.example.crypto_app_socket.domain.repository.DetailRepository
import com.example.crypto_app_socket.domain.uimodel.CoinUiModel
import javax.inject.Inject

class FetchCoinDetailUseCase @Inject constructor(val detailRepository: DetailRepository) {

    suspend operator fun invoke(id : String) : ContentState<CoinUiModel>{
        return detailRepository.getDetailRepository(id = id)
    }
}