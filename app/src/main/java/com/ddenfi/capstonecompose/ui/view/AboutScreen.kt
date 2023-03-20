package com.ddenfi.capstonecompose.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddenfi.capstonecompose.R
import com.ddenfi.capstonecompose.ui.components.HeaderComp
import com.ddenfi.capstonecompose.ui.theme.BaseBlack
import com.ddenfi.capstonecompose.ui.theme.Shapes
import com.ddenfi.capstonecompose.ui.theme.TextWhite
import com.ddenfi.capstonecompose.ui.theme.lexend

@Composable
fun AboutScreen(modifier: Modifier = Modifier) {
    Column (modifier.padding(horizontal = 16.dp)){
        AboutHeaderSection()
        ProfileSection()
    }
}

@Composable
fun AboutHeaderSection() {
    HeaderComp(
        text = "About:Me",
        delimiter = ':',
        painter = painterResource(id = R.drawable.ic_about_me),
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_me),
            contentDescription = "Creator Profile Photo",
            modifier = Modifier
                .size(250.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(Shapes.medium)
                .background(Color.White.copy(alpha = 0.07f))
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Deki Nur Fitrian",
                style = MaterialTheme.typography.h4,
                color = TextWhite,
                fontFamily = lexend,
                fontWeight = FontWeight.Bold,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(Shapes.medium)
                .background(Color.White.copy(alpha = 0.07f))
                .padding(vertical = 8.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "A person who interested in technology, science and business",
                style = MaterialTheme.typography.body1,
                color = Color.White,
                fontFamily = lexend,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Justify
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(Shapes.medium)
                .background(Color.White.copy(alpha = 0.07f))
                .padding(vertical = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Business Administration - UNDIP",
                style = MaterialTheme.typography.body1,
                color = Color.White,
                fontFamily = lexend,
                fontWeight = FontWeight.Normal,
            )
            Text(
                text = "Bangkit Academy 22 Graduate",
                style = MaterialTheme.typography.body1,
                color = Color.White,
                fontFamily = lexend,
                fontWeight = FontWeight.Normal,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Contact Me",
                fontSize = 12.sp,
                color = Color.White,
                fontFamily = lexend,
                fontWeight = FontWeight.Normal,
            )
            Text(
                text = "deki.nfi@gmail.com",
                style = MaterialTheme.typography.body1,
                color = Color.White,
                fontFamily = lexend,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "0898 0978 900",
                style = MaterialTheme.typography.body1,
                color = Color.White,
                fontFamily = lexend,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPrev(
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .background(BaseBlack)
            .padding(16.dp)
    ) {
        AboutScreen()
    }
}