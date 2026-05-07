package com.example.crypto_app_socket.data.socket

import android.util.Log
import com.example.crypto_app_socket.data.model.CoinUpdateModel
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.json.JSONObject
import javax.inject.Inject


class SocketService @Inject constructor(val socket: Socket) {

    private var isConnected = false


    fun connect() {
        if(!isConnected) {
            socket.connect()
            isConnected = true


            socket.on(Socket.EVENT_CONNECT) {
                Log.e("SOCKET_LOG", "BAĞLANDI! ID: ${socket.id()}")
            }

            socket.on(Socket.EVENT_CONNECT_ERROR) { args ->
                val error = args[0] as? Exception
                Log.e("SOCKET_LOG", "BAĞLANTI XƏTASI: ${error?.message ?: "Naməlum xəta"}")
            }

            socket.on(Socket.EVENT_DISCONNECT) { args ->
                Log.e("SOCKET_LOG", "BAĞLANTI KƏSİLDİ: ${args[0]}")
            }
            socket.on(Socket.EVENT_CONNECT_ERROR) { args ->
                val err = args[0]
                Log.e("SOCKET_LOG", "Xəta detalları: $err") // Burada '403' və ya '400' görsən, deməli server səni rədd edir
            }




        }
    }

    fun disconnect() {
        socket.disconnect()
        isConnected = false
    }

    fun subscribeCryptoList(cryptoList: List<String>) {
        val jsonArray = org.json.JSONArray(cryptoList)
        Log.e("subscribeList", "subscribeList")
        socket.emit("subscribeCryptoList", jsonArray)
    }

    fun subscribeDetail(id: String) {
        socket.emit("subscribeDetail", id)
    }

    fun observeUpdates(event: String): Flow<CoinUpdateModel> = callbackFlow {
        val listener = Emitter.Listener { args ->
            val json = args[0] as JSONObject
            val update = CoinUpdateModel(
                name = json.getString("name"),
                price = json.getDouble("price"),
                timestamp = json.getString("timestamp")
            )
            trySend(update)
            Log.e("UPDATE DATA", update.price.toString())

        }

        socket.on(event, listener)
        awaitClose {
            socket.off(event, listener)
        }
    }


}