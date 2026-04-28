package com.example.crypto_app_socket.presentation.home

import com.example.crypto_app_socket.domain.uimodel.CoinUiModel

object HomeContract{

    sealed interface Effect {
        data class ShowError(val message : String) : Effect
    }

    data class State(
        val allCoinsList : List<CoinUiModel> = emptyList()
    )
}