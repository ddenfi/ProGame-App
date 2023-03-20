package com.ddenfi.capstonecompose.ui.components

import androidx.annotation.DrawableRes

data class PlatformsItem(
    var id:Int = 0,
    var name: String = "",
    @DrawableRes var icon: Int = 0
)
