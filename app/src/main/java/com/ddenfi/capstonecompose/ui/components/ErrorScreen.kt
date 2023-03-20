package com.ddenfi.capstonecompose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ddenfi.capstonecompose.ui.theme.BaseBlack
import com.ddenfi.capstonecompose.ui.theme.PurpleButtonSelected
import com.ddenfi.capstonecompose.ui.theme.TextWhite

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    errorMessage: String,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        backgroundColor = TextWhite.copy(alpha = 0.1f)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Error",
                tint = PurpleButtonSelected
            )
            Text(text = errorMessage, color = TextWhite)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPrev() {
    ErrorScreen(errorMessage = "No Internet Connection")
}