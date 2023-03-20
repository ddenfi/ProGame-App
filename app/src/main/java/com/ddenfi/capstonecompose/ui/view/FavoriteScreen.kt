package com.ddenfi.capstonecompose.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ddenfi.capstonecompose.R
import com.ddenfi.capstonecompose.domain.model.FavGameScreenState
import com.ddenfi.capstonecompose.domain.model.GameModel
import com.ddenfi.capstonecompose.ui.components.ErrorScreen
import com.ddenfi.capstonecompose.ui.components.HeaderComp
import com.ddenfi.capstonecompose.ui.components.ItemGame
import com.ddenfi.capstonecompose.ui.components.LoadingScreen
import com.ddenfi.capstonecompose.ui.viewmodel.HomeViewModel

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    navToDetail: (gameId: Int) -> Unit,
) {
    val favGameState by viewModel.favGameScreenState.collectAsState()
    LaunchedEffect(key1 = true) {
        viewModel.getAllFavGame()
    }
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        FavoriteHeaderSection()
        if (favGameState.hasError){
            ErrorScreen(errorMessage = favGameState.errorMessage ?: "Unknown Error Occurred!")
        } else if (favGameState.isLoading){
            LoadingScreen()
        } else if (favGameState.data.isNullOrEmpty()){
            EmptyFavoriteListGame()
        } else {
            FavoriteListGame(navToDetail = navToDetail, favGameScreenState = favGameState)
        }
    }
}

@Composable
fun FavoriteHeaderSection(
    modifier: Modifier = Modifier
) {
    HeaderComp(
        text = "Fav:game",
        delimiter = ':',
        painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
        modifier = modifier.padding(vertical = 8.dp),
    )
}

@Composable
fun FavoriteListGame(
    modifier: Modifier = Modifier,
    navToDetail: (gameId: Int) -> Unit,
    favGameScreenState: FavGameScreenState,
) {
    val items = favGameScreenState.data ?: emptyList()
    LazyColumn(modifier) {
        items(items = items, key = { item: GameModel -> item.id }) { item: GameModel ->
            ItemGame(modifier = Modifier.clickable {navToDetail(item.id)}, game = item)
        }
    }
}

@Composable
fun EmptyFavoriteListGame() {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url("https://assets5.lottiefiles.com/packages/lf20_eq1aIIplXI.json"))
    LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
}