package com.example.crypto_app_socket.core.di

import android.app.Application
import android.util.Log
import com.example.crypto_app_socket.data.socket.SocketService
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject lateinit var socketService: SocketService

    override fun onCreate() {
        super.onCreate()
        socketService.connect()

    }

}