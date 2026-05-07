package com.example.crypto_app_socket.presentation.detail

import com.example.crypto_app_socket.domain.uimodel.CoinUiModel
import com.example.crypto_app_socket.domain.uimodel.CoinUpdateUiModel
import com.example.crypto_app_socket.presentation.detail.model.ChartPointModel

object DetailContract {

    sealed interface Intent {
        data class GetCoinDetail (val id : String) : Intent
    }

    sealed interface Effect {
        data class ShowError(val message : String) : Effect
    }

    data class State(
        val coinDetail : CoinUiModel = CoinUiModel.empty,
        val historyList : List<CoinUpdateUiModel> = emptyList(),
        val chartHistoryList : List<ChartPointModel> = emptyList(),
        val updatedPercentChange : List<String> = emptyList()
    )
}