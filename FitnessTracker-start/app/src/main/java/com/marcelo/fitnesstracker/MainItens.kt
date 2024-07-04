package com.marcelo.fitnesstracker

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MainItens(
    val id: Int,
    @DrawableRes val drawableId: Int,
    @StringRes val textString: Int,
    @ColorRes val color: Int
)
