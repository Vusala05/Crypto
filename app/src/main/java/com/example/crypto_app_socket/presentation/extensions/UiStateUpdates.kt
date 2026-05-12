package com.example.crypto_app_socket.presentation.extensions

import androidx.compose.ui.graphics.Color
import com.example.crypto_app_socket.core.Drawables
import com.example.crypto_app_socket.core.util.percentFormat
import com.example.crypto_app_socket.domain.uimodel.ComingUpdatedData
import com.example.crypto_app_socket.ui.theme.AppColors

fun ComingUpdatedData.lineIconChange() : Int {
    this.isUp?.let {
       return  if (this.isUp!!) Drawables.increasing_line
        else if (this.percentChange == 0.0) Drawables.stabil__line
        else Drawables.decreasing_line
    }
    return Drawables.stabil__line
}
fun ComingUpdatedData.circleIconChange() : Int {
    this.isUp?.let {
        return if (isUp!!) Drawables.arrow_circle_increasing
        else if(this.percentChange == 0.0) Drawables.arrow_circle_stabil
        else Drawables.arrow_circle_decreasing
    }
    return Drawables.arrow_circle_stabil
}

fun ComingUpdatedData.colorStateChange(colors : AppColors) : Color {
    this.isUp?.let {
        return if (this.isUp!!) colors.onBackgroundUp
        else if (this.percentChange == 0.0) colors.stableColor
        else colors.onBackgroundDown
    }
    return colors.stableColor
}

fun ComingUpdatedData.changeTextState() : String {
    this.isUp?.let {
        return if (this.isUp!!) "+${this.percentChange.percentFormat()}%"
        else "${this.percentChange.percentFormat()}%"
    }
    return "${this.percentChange.percentFormat()}%"
}