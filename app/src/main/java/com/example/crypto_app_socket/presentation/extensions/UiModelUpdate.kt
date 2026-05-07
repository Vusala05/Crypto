package com.example.crypto_app_socket.presentation.extensions

import com.example.crypto_app_socket.domain.uimodel.CoinUiModel
import com.example.crypto_app_socket.domain.uimodel.CoinUpdateUiModel

fun CoinUiModel.updateWith(updatedData: CoinUpdateUiModel): Pair<CoinUpdateUiModel,CoinUiModel> {
    if (this.id == updatedData.name){

        val isUpCurrently = updatedData.price > this.price

        val percentChange = if(this.price!=0.0){
            ((updatedData.price - this.price)/this.price) * 100
        } else 0.0

        val historyData = updatedData.copy(percentChange = percentChange, isUp = isUpCurrently)

       val coinUpdatedUiModel = this.copy(
           price = updatedData.price,
           percentChange = percentChange,
           isUp = isUpCurrently
       )

        return Pair(historyData,coinUpdatedUiModel)

    }
    else return Pair(updatedData,this)


}