package com.example.crypto_app_socket.presentation.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto_app_socket.core.ContentState
import com.example.crypto_app_socket.data.model.CoinUpdateModel
import com.example.crypto_app_socket.domain.usecases.FetchCoinDetailUseCase
import com.example.crypto_app_socket.domain.usecases.ObserveDetailUpdateUseCase
import com.example.crypto_app_socket.domain.usecases.SubscribeCoinDetailUseCase
import com.example.crypto_app_socket.presentation.detail.model.ChartPointModel
import com.example.crypto_app_socket.presentation.extensions.updateWith
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
    val fetchCoinDetailUseCase: FetchCoinDetailUseCase,
    val observeDetailUpdateUseCase: ObserveDetailUpdateUseCase,
    val subscribeCoinDetailUseCase: SubscribeCoinDetailUseCase
) : ViewModel() {

    init {

        observeUpdateData()
    }

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
                    Log.e("errorrrDetail", res.message)
                }
                is ContentState.Success -> {
                    _state.update { it.copy(coinDetail = res.data) }
                    Log.e("DETAIL_ID", id)
                    subscribeCoinDetailUseCase(id = id)
                }

            }
        }
    }

    private fun observeUpdateData() {
        viewModelScope.launch {
            observeDetailUpdateUseCase().collect { updatedData ->

                _state.update { currentState ->
                    val (updatedHistoryList, updatedDetailData) = currentState.coinDetail.updateWith(updatedData)
                    val newHistoryList = (listOf(updatedHistoryList) + currentState.historyList).take(50)
                    val newChartList = (currentState.historyList + listOf(updatedData)).take(50)
                    val chartPointList = newChartList.mapIndexed { index, model ->
                        ChartPointModel(
                            x = index.toFloat(),
                            y = model.price.toFloat()
                        )
                    }

                    currentState.copy(historyList = newHistoryList,
                        coinDetail = updatedDetailData,
                        chartHistoryList = chartPointList)

                }
            }
        }
    }




}