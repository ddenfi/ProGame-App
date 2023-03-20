package com.ddenfi.capstonecompose.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.ddenfi.capstonecompose.ui.theme.PurpleButtonSelected
import com.ddenfi.capstonecompose.ui.theme.lexend

@Composable
fun HeaderComp(
    text:String,
    delimiter:Char,
    painter:Painter,
    modifier:Modifier = Modifier
) {
    val context = LocalContext.current
    Row(
        modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.ExtraBold,
                        color = PurpleButtonSelected,
                        fontFamily = lexend,
                        fontSize = MaterialTheme.typography.h4.fontSize
                    )
                ) {
                    append(text.substringBefore(delimiter))
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Medium,
                        color = PurpleButtonSelected,
                        fontFamily = lexend,
                        fontSize = MaterialTheme.typography.h4.fontSize
                    )
                ) {
                    append(text.substringAfter(delimiter))
                }
            })

        IconButton(
            onClick = {
                      Toast.makeText(context,"This menu will coming soon!",Toast.LENGTH_SHORT).show()
                      },
            content = {
                Icon(
                    painter = painter,
                    contentDescription = "Notification",
                    tint = Color.White,
                )
            })
    }
}