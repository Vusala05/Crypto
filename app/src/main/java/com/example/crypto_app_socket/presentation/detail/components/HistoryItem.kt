package com.example.crypto_app_socket.presentation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.crypto_app_socket.core.BaseTheme
import com.example.crypto_app_socket.core.util.percentFormat
import com.example.crypto_app_socket.core.util.toDate
import com.example.crypto_app_socket.core.util.toTime
import com.example.crypto_app_socket.domain.uimodel.CoinUpdateUiModel
import com.example.crypto_app_socket.ui.theme.LocalColors

@Composable
fun HistoryItem(
    updateModel: CoinUpdateUiModel
) {
    val colors = LocalColors.current
    val isUp = updateModel.isUp ?: false
    val percent = updateModel.percentChange.percentFormat()
    val time = updateModel.timestamp.toTime()
    val date = updateModel.timestamp.toDate()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(BaseTheme.dimens.dp2),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Text(text = time, color = Color.White, style = BaseTheme.textStyle.t16.copy(fontWeight = FontWeight.W400))
            Spacer(modifier = Modifier.width(BaseTheme.dimens.dp01))
            Text(text = date, color = Color.Gray, style = BaseTheme.textStyle.t14)
        }
        Text(text = if(isUp) "+$percent%" else "$percent%" ,
            color = if(isUp) colors.onBackgroundUp
            else if (updateModel.percentChange == 0.0) colors.stableColor
            else colors.onBackgroundDown,
            style = BaseTheme.textStyle.t12)

    }
}