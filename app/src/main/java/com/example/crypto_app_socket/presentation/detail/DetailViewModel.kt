package com.example.crypto_app_socket.presentation.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto_app_socket.core.ContentState
import com.example.crypto_app_socket.domain.usecases.FetchCoinDetailUseCase
import com.example.crypto_app_socket.presentation.home.HomeContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    val fetchCoinDetailUseCase: FetchCoinDetailUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DetailContract.State())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<DetailContract.Effect>()
    val effect = _effect.asSharedFlow()


    fun onIntent(intent : DetailContract.Intent){
        when(intent){
            is DetailContract.Intent.GetCoinDetail ->{
                loadCoinDetail(intent.id)
            }
        }
    }

    private fun loadCoinDetail(id : String){
        viewModelScope.launch {
            when(val res = fetchCoinDetailUseCase(id = id)) {
                is ContentState.Error -> {
                    _effect.emit(DetailContract.Effect.ShowError(res.message))
                    Log.e("errorrr", res.message)
                }
                is ContentState.Success -> {
                    _state.update { it.copy(coinDetail = res.data) }

                }

            }
        }
    }


}