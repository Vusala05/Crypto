package com.example.crypto_app_socket.ui.theme

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.crypto_app_socket.ui.navigation.MainRoutes

@Composable
fun App(){
    val navController = rememberNavController()

    Scaffold() { innerPadding ->
        MainRoutes(
            navController = navController,
            paddingValues = innerPadding)

    }
}