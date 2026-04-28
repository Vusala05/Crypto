package com.example.crypto_app_socket.data.service

import com.example.crypto_app_socket.core.constants.ApiConstants
import com.example.crypto_app_socket.data.model.CoinDetailResponse
import com.example.crypto_app_socket.data.model.CoinResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(ApiConstants.GET_COINS)
    suspend fun getAllCoins() : CoinResponseModel

    @GET(ApiConstants.GET_COIN_DETAIL)
    suspend fun getCoinDetail(
        @Path("id") id : String
    ) : CoinDetailResponse

}