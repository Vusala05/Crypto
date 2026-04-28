package com.example.crypto_app_socket.core.util

fun Double?.priceFormat() : String {
    return String.format("%,.2f",this)
}

fun Double?.percentFormat() : String {
    return String.format("%.2f",this)
}