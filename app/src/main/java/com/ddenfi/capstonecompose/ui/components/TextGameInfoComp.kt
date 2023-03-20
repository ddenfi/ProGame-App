package com.ddenfi.capstonecompose.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.ddenfi.capstonecompose.ui.theme.PurpleButtonSelected

@Composable
fun TextGameItemInfo(
    text: AnnotatedString,
    @DrawableRes icon: Int,
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        Icon(painter = painterResource(id = icon), contentDescription = text.toString(), tint = PurpleButtonSelected)
        Text(text = text)
    }
}