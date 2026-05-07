package com.example.crypto_app_socket.core.di

import com.example.crypto_app_socket.core.constants.ApiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.socket.client.IO
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SocketModule {

    @Provides
    @Singleton
    fun provideWebSocketClient(): io.socket.client.Socket = IO.socket(
        ApiConstants.SOCKET_URL,
        IO.Options.builder().apply {
            setTransports(arrayOf("websocket"))
            setUpgrade(true)
            setRememberUpgrade(true)
            setForceNew(true)
            setReconnection(true)
            setAuth(null)
        }.build()
    )

}