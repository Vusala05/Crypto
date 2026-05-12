package com.example.crypto_app_socket.presentation.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.crypto_app_socket.core.BaseTheme
import com.example.crypto_app_socket.core.Drawables
import com.example.crypto_app_socket.core.Strings
import com.example.crypto_app_socket.core.util.percentFormat
import com.example.crypto_app_socket.core.util.priceFormat
import com.example.crypto_app_socket.presentation.detail.components.CryptoChart
import com.example.crypto_app_socket.presentation.detail.components.HistoryItem
import com.example.crypto_app_socket.presentation.extensions.changeTextState
import com.example.crypto_app_socket.presentation.extensions.circleIconChange
import com.example.crypto_app_socket.presentation.extensions.colorStateChange
import com.example.crypto_app_socket.ui.theme.LocalColors

@Composable
fun DetailContent(
    state: DetailContract.State,
    onClick : () -> Unit
    ){
    val colors = LocalColors.current
    val colorState = state.coinDetail.colorStateChange(colors)
    val textState = state.coinDetail.changeTextState()
    val iconState = state.coinDetail.circleIconChange()

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = BaseTheme.dimens.dp03,
                        vertical = BaseTheme.dimens.dp02)
            ) {

                Icon(
                    painter = painterResource(Drawables.back_btn),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable(onClick = onClick)
                        .size(BaseTheme.dimens.backBtn)
                        .align(Alignment.CenterStart)
                )

                Text(
                    text = stringResource(Strings.markets),
                    style = BaseTheme.textStyle.t18Bold.copy(color = colors.onBackgroundText),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)
            .padding(top = BaseTheme.dimens.dp07),
            verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp02)) {
            Text(text = "${state.coinDetail.longName}(${state.coinDetail.shortName})",
                modifier = Modifier.padding(start = BaseTheme.dimens.dp04),
                style = BaseTheme.textStyle.t14Bold.copy(color = colors.onBackgroundText))
            Text(
                text = "$${state.coinDetail.price.priceFormat()}",
                modifier = Modifier.padding(start = BaseTheme.dimens.dp04),
                color = Color.White,
                style = BaseTheme.textStyle.t28Bold.copy(color = colors.onBackgroundText),
            )
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(start = BaseTheme.dimens.dp04)) {
                Icon(painter = painterResource(iconState),
                    contentDescription = null,
                    tint = state.coinDetail.colorStateChange(colors),
                    modifier = Modifier.size(BaseTheme.dimens.arrow_circle))

                Spacer(modifier = Modifier.height(BaseTheme.dimens.dp01))
                Text(text = textState ,
                    color = colorState,
                    style = BaseTheme.textStyle.t14.copy(fontWeight = FontWeight.W500))
            }
            Spacer(modifier = Modifier.height(BaseTheme.dimens.dp05))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(BaseTheme.dimens.detailGraphHeight)

            ) {
                CryptoChart(state.chartHistoryList)
            }

            Spacer(modifier = Modifier.height(BaseTheme.dimens.dp016))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(Strings.history),
                style = BaseTheme.textStyle.t16.copy(fontWeight = FontWeight.W600, color = colors.onBackgroundText),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(BaseTheme.dimens.dp04))
            HorizontalDivider(
                Modifier.fillMaxWidth().padding(horizontal = BaseTheme.dimens.dp04),
                thickness = 0.dp,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(BaseTheme.dimens.dp02))

            LazyColumn(modifier = Modifier.fillMaxWidth().padding(horizontal = BaseTheme.dimens.dp04)) {
                items(state.historyList) { historyItem ->
                    HistoryItem(
                        updateModel = historyItem
                    )
                }
            }

        }


    }


    }

