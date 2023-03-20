package com.ddenfi.capstonecompose.ui.view

import android.os.Build
import android.text.Html
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ddenfi.capstonecompose.R
import com.ddenfi.capstonecompose.domain.model.GameDetailScreenState
import com.ddenfi.capstonecompose.ui.components.*
import com.ddenfi.capstonecompose.ui.theme.*
import com.ddenfi.capstonecompose.ui.viewmodel.DetailViewModel
import com.ddenfi.capstonecompose.ui.viewmodel.HomeViewModel
import com.ddenfi.capstonecompose.utils.DataMapper
import com.ddenfi.capstonecompose.utils.shimmerEffect

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun DetailGameScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel(),
    gameId: Int,
) {
    LaunchedEffect(key1 = true) {
        viewModel.getDetailGame(gameId)
    }
    val gameDetailScreenState by viewModel.detailGameState.collectAsState()


    Column(modifier) {
        GameDetailHeaderSection(
            game = gameDetailScreenState,
            onFavButtonClick = { isFav ->
                viewModel.setFavGame(gameId, isFav)
            })
        if (gameDetailScreenState.isLoading) {
            DetailScreenLoading()
        } else if (gameDetailScreenState.hasError) {
            ErrorScreen(errorMessage = gameDetailScreenState.errorMessage ?: "Unknown Error Occurred!")
        } else {
            GameDetailDetailedSection(game = gameDetailScreenState)
        }
    }
}

@Composable
fun GameDetailHeaderSection(
    modifier: Modifier = Modifier,
    game: GameDetailScreenState,
    onFavButtonClick: (Boolean) -> Unit
) {
    val isFav = game.data?.isFavorite ?: false
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.35f),
        contentAlignment = Alignment.BottomStart
    ) {
        AsyncImage(
            model = game.data?.backgroundImage,
            contentDescription = game.data?.name,
            placeholder = painterResource(id = R.drawable.ic_baseline_image_24),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            BaseBlack,
                        ),
                        startY = 200f
                    )
                )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.75f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = game.data?.name ?: "Game Name",
                    style = MaterialTheme.typography.h4,
                    fontFamily = lexend,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
                val reviewsCount = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontFamily = lexend,
                            color = TextWhite
                        )
                    ) {
                        append(game.data?.reviewsCount.toString())
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
                        append(game.data?.rating.toString())
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
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    TextGameItemInfo(text = reviewsCount, icon = R.drawable.ic_user)
                    TextGameItemInfo(text = rating, icon = R.drawable.ic_rating)
                }
            }
            Button(
                onClick = { onFavButtonClick(!isFav) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = PurpleButtonSelected
                )
            ) {
                Icon(
                    imageVector = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Make a Favorite",
                    tint = TextWhite
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun GameDetailDetailedSection(
    modifier: Modifier = Modifier,
    game: GameDetailScreenState
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val desc = game.data?.let {
            Html.fromHtml(it.description, Html.FROM_HTML_MODE_COMPACT).toString()

        }
        Text(
            text = "Description",
            fontSize = 18.sp,
            fontFamily = lexend,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = desc ?: "",
            style = MaterialTheme.typography.body2,
            fontFamily = lexend,
            color = Color.White,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Justify
        )
        Text(
            text = "About Game",
            fontSize = 18.sp,
            fontFamily = lexend,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Released Date",
                    fontSize = 12.sp,
                    fontFamily = lexend,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = game.data?.releasedDate ?: "",
                    fontSize = 12.sp,
                    fontFamily = lexend,
                    color = Color.White,
                    fontWeight = FontWeight.Normal
                )
            }
            Text(
                text = "Platforms",
                fontSize = 12.sp,
                fontFamily = lexend,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                val gamePlatform = game.data?.parentPlatform?.map {
                    DataMapper.parentPlatformsToPlatformItem(it)
                } ?: emptyList()
                gamePlatform.forEach {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = it.name,
                        tint = Color.White,
                        modifier = Modifier
                            .alpha(0.75f)
                            .size(35.dp)
                    )
                }
            }
        }

    }
}

@Composable
fun DetailScreenLoading(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .height(20.dp)
                .clip(RoundedCornerShape(5.dp))
                .shimmerEffect()
        )
        Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
            repeat(10){
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .shimmerEffect()
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .height(20.dp)
                .clip(RoundedCornerShape(5.dp))
                .shimmerEffect()
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(16.dp)
                .clip(RoundedCornerShape(5.dp))
                .shimmerEffect()
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .height(16.dp)
                .clip(RoundedCornerShape(5.dp))
                .shimmerEffect()
        )
    }
}

@Preview
@Composable
fun DetailScreeenLoadingPrev() {
    DetailScreenLoading()
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun DetailScreenPrev(
    @PreviewParameter(DetailScreenDataProvider::class) game: GameDetailScreenState
) {
    Surface(Modifier.fillMaxSize(), color = BaseBlack) {
        Column(
            Modifier
                .fillMaxSize()
        ) {
            GameDetailHeaderSection(game = game) {}
            GameDetailDetailedSection(game = game)
        }
    }
}