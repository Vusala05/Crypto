package com.example.crypto_app_socket.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@Composable
fun DetailView(
    navController: NavController,
    id : String,
    viewModel: DetailViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.onIntent(DetailContract.Intent.GetCoinDetail(id = id))
    }
    DetailContent(
        state = state,
        )

}