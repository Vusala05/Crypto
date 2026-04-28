package com.example.crypto_app_socket.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto_app_socket.core.ContentState
import com.example.crypto_app_socket.domain.usecases.FetchAllCoinsUseCase
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
    val fetchAllCoinsUseCase: FetchAllCoinsUseCase
) : ViewModel(){

    init {
        loadAllCoins()
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
                Log.e("errorrr", res.message)
            }
            is ContentState.Success -> {
                _state.update { it.copy(allCoinsList = res.data) }

        }

            }
        }
    }
}