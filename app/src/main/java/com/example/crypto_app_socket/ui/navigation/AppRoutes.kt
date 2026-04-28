package com.example.crypto_app_socket.ui.navigation

import kotlinx.serialization.Serializable

sealed interface AppRoutes {

    @Serializable
    data object Home : AppRoutes

    @Serializable
    data class Detail(val id : String) : AppRoutes {

    }
}
