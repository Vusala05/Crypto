package com.example.crypto_app_socket.data.socket

import com.example.crypto_app_socket.data.model.CoinUpdateModel
import io.socket.client.Socket
import org.json.JSONObject

class SocketService(val socket: Socket) {

    fun connect(){
        socket.connect()
    }

    fun disconnect(){
        socket.disconnect()
    }
    //subscribeCryptoList

    fun subscribeCryptoList(cryptoList : List<String>){
        socket.emit("subscribeCryptoList",cryptoList)
    }

    fun subscribeDetail(id : String){
        socket.emit("subscribeDetail",id)
    }

    fun observeUpdates (event : String,onUpdate : (CoinUpdateModel) -> Unit){

        socket.on(event){ args ->
            val json = args[0] as JSONObject

            val update = CoinUpdateModel(
                name = json.getString("name"),
                price = json.getDouble("price"),
                timestamp = json.getString("timestamp")
            )
            onUpdate(update)
        }
    }


}