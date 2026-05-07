package com.example.crypto_app_socket.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.crypto_app_socket.core.BaseTheme
import com.example.crypto_app_socket.core.Strings
import com.example.crypto_app_socket.presentation.home.components.CoinItem
import com.example.crypto_app_socket.ui.theme.LocalColors
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun HomeContent(
   state : HomeContract.State,
   effect: SharedFlow<HomeContract.Effect>,
   navigateToDetail : (String) -> Unit
){
    val context = LocalContext.current
    val colors = LocalColors.current

    LaunchedEffect(effect) {
        effect.collect {
            when(it){
                is HomeContract.Effect.ShowError ->{
                    Toast.makeText(context,it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    Scaffold(
        modifier = Modifier.padding(horizontal = BaseTheme.dimens.dp04)
            .padding(top = BaseTheme.dimens.dp010),
        topBar = {
            Text(
                text = stringResource(Strings.markets),
                style = BaseTheme.textStyle.t28.copy(color = colors.onBackgroundText),
                modifier = Modifier.padding(bottom = BaseTheme.dimens.dp05))
        }
    ) { innerPadding->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
        items(state.allCoinsList){ coin->
            CoinItem(
                 coinUiModel = coin,
                onClick = navigateToDetail
                )
            HorizontalDivider(modifier = Modifier.fillMaxWidth()
                .width(1.dp))
        }


        }

    }

}