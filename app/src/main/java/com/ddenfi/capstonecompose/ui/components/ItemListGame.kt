package com.ddenfi.capstonecompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ddenfi.capstonecompose.R
import com.ddenfi.capstonecompose.domain.model.GameModel
import com.ddenfi.capstonecompose.ui.theme.Shapes
import com.ddenfi.capstonecompose.ui.theme.TextWhite
import com.ddenfi.capstonecompose.ui.theme.TextWhite3
import com.ddenfi.capstonecompose.ui.theme.lexend
import com.ddenfi.capstonecompose.utils.shimmerEffect

@Composable
fun ItemGame(
    modifier: Modifier,
    game: GameModel,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = game.backgroundImage,
            contentDescription = game.name,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_baseline_image_24),
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
                .clip(Shapes.medium),
        )
        Column {
            Text(
                text = game.name,
                fontFamily = lexend,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                val reviewsCount = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontFamily = lexend,
                            color = TextWhite
                        )
                    ) {
                        append(game.reviewsCount.toString())
                        append("M")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontFamily = lexend,
                            color = TextWhite3
                        )
                    ) {
                        append(" Reviews")
                    }
                }
                val rating = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontFamily = lexend,
                            color = TextWhite
                        )
                    ) {
                        append(game.rating.toString())
                    }
                    withStyle(
                        style = SpanStyle(
                            fontFamily = lexend,
                            color = TextWhite3
                        )
                    ) {
                        append(" Rating")
                    }
                }
                TextGameItemInfo(text = reviewsCount, icon = R.drawable.ic_user)
                TextGameItemInfo(text = rating, icon = R.drawable.ic_rating)
            }
        }
    }
}

@Composable
fun ItemGameLoading(
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .height(80.dp)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxSize()
                .clip(RoundedCornerShape(5.dp))
                .shimmerEffect()
        )
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(horizontal = 10.dp).fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp))
                    .shimmerEffect()
            )
            Box(
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth(0.8f)
                    .clip(RoundedCornerShape(5.dp))
                    .shimmerEffect()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemGameLoadingPrev() {
    ItemGameLoading()
}