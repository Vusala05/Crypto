package com.example.crypto_app_socket.core

import com.example.crypto_app_socket.core.constants.BaseTextStyle
import com.example.crypto_app_socket.core.constants.Dimens

abstract class BaseTheme {

   companion object{
       val dimens : Dimens
           get() = Dimens

       val textStyle : BaseTextStyle
           get() = BaseTextStyle
   }
}