package com.example.crypto_app_socket.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.crypto_app_socket.presentation.detail.DetailView
import com.example.crypto_app_socket.presentation.home.HomeView

@Composable
fun MainRoutes(
    navController : NavHostController,
    paddingValues : PaddingValues
){


    NavHost(
        navController = navController,
        startDestination = AppRoutes.Home,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable<AppRoutes.Home>{
            HomeView(navController = navController)
        }

        composable<AppRoutes.Detail> { backStackEntry ->
            val args = backStackEntry.toRoute<AppRoutes.Detail>()
            DetailView(
                navController = navController,
                id = args.id
                )
        }
    }

}