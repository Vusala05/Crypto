package com.example.crypto_app_socket.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto_app_socket.core.ContentState
import com.example.crypto_app_socket.domain.usecases.FetchAllCoinsUseCase
import com.example.crypto_app_socket.domain.usecases.ObserveCoinsUpdateUseCase
import com.example.crypto_app_socket.domain.usecases.SubscribeAllCoinsUseCase
import com.example.crypto_app_socket.presentation.extensions.updateWith
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val fetchAllCoinsUseCase: FetchAllCoinsUseCase,
    val observeCoinsUpdateUseCase: ObserveCoinsUpdateUseCase,
    val subscribeAllCoinsUseCase: SubscribeAllCoinsUseCase
) : ViewModel(){

    init {
        loadAllCoins()
        observeUpdatedCoins()
    }

    private val _state = MutableStateFlow(HomeContract.State())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<HomeContract.Effect>()
    val effect = _effect.asSharedFlow()



     private fun loadAllCoins(){
         viewModelScope.launch {
        when(val res = fetchAllCoinsUseCase()) {
            is ContentState.Error -> {
                _effect.emit(HomeContract.Effect.ShowError(res.message))
            }
            is ContentState.Success -> {
                val coins = res.data
                _state.update { it.copy(allCoinsList = coins) }
                subscribeAllCoinsUseCase(coins.map { it.id })
            }

            }
        }
    }

    private fun observeUpdatedCoins(){
        viewModelScope.launch {
            observeCoinsUpdateUseCase().collect { updatedCoins ->
                _state.update { currentState->
                    Log.e("timeeee", updatedCoins.timestamp)
                    val updatedData = currentState.allCoinsList.map { it.updateWith(updatedCoins).second }

                    currentState.copy(allCoinsList = updatedData)
                }

            }
        }
    }
}