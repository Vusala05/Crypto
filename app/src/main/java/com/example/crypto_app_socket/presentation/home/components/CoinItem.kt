package com.example.crypto_app_socket.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import com.example.crypto_app_socket.core.BaseTheme
import com.example.crypto_app_socket.core.Drawables
import com.example.crypto_app_socket.core.util.percentFormat
import com.example.crypto_app_socket.core.util.priceFormat
import com.example.crypto_app_socket.domain.uimodel.CoinUiModel
import com.example.crypto_app_socket.ui.theme.LocalColors

@Composable
fun CoinItem(
    modifier: Modifier = Modifier,
    coinUiModel: CoinUiModel,
    onClick : (String) -> Unit
) {
    val colors = LocalColors.current
    Box(modifier = Modifier.padding(vertical = BaseTheme.dimens.dp04)) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    onClick(coinUiModel.id)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp01)
            ) {
                AsyncImage(
                    model = coinUiModel.image,
                    contentDescription = null,
                    modifier = Modifier.size(BaseTheme.dimens.logoSize)
                        .clip(shape = CircleShape)
                )
                Column {
                    Text(
                        text = coinUiModel.shortName,
                        style = BaseTheme.textStyle.t16.copy(color = colors.onBackgroundText)
                    )
                    Text(
                        text = coinUiModel.longName,
                        style = BaseTheme.textStyle.t14.copy(color = colors.onBackgroundText)
                    )
                }


            }
            Image(
                    painter = painterResource(
                        if (coinUiModel.isUp) Drawables.increasing_line
                        else if(coinUiModel.percentChange== 0.0) Drawables.stabil__line
                        else Drawables.decreasing_line
                    ),
                    modifier = Modifier.size(
                        BaseTheme.dimens.graphWidth,
                        BaseTheme.dimens.graphHeight
                    ),
                    contentDescription = null
                )



            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(BaseTheme.dimens.dp01)
            ) {
                Text(
                    text = coinUiModel.price.priceFormat(),
                    style = BaseTheme.textStyle.t16.copy(color = colors.onBackgroundText)
                )
                Text(
                    text = if (coinUiModel.isUp) "+${coinUiModel.percentChange.percentFormat()}%"
                    else "${coinUiModel.percentChange.percentFormat()}%",
                    color = if (coinUiModel.isUp) colors.onBackgroundUp
                    else if(coinUiModel.percentChange== 0.0)  colors.stableColor
                        else colors.onBackgroundDown
                )

            }

        }

    }
}