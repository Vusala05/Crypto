package com.example.crypto_app_socket.presentation.detail

import com.example.crypto_app_socket.domain.uimodel.CoinUiModel
import com.example.crypto_app_socket.presentation.home.HomeContract

object DetailContract {

    sealed interface Intent {

    }

    sealed interface Effect {
        data class ShowError(val message : String) : Effect
    }

    data class State(
        val coinDetail : CoinUiModel = CoinUiModel.empty
    )
}