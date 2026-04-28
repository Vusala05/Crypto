package com.example.crypto_app_socket.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.crypto_app_socket.ui.navigation.AppRoutes

@Composable
fun HomeView(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()
  HomeContent(
      state = state,
      effect = viewModel.effect,
      ){
      navController.navigate(AppRoutes.Detail(it))
  }
}