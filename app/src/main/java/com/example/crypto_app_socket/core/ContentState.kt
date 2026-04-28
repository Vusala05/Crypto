package com.example.crypto_app_socket.core

sealed class ContentState <T> {
    data class Success<T> (val data : T ) : ContentState<T>()
    data class Error<T> (val message : String) : ContentState<T>()

}