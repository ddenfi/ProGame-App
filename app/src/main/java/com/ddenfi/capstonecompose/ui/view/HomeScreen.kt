package com.ddenfi.capstonecompose.ui.view

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddenfi.capstonecompose.R
import com.ddenfi.capstonecompose.ui.components.ErrorScreen
import com.ddenfi.capstonecompose.ui.components.HeaderComp
import com.ddenfi.capstonecompose.ui.components.ItemGame
import com.ddenfi.capstonecompose.ui.components.ItemGameLoading
import com.ddenfi.capstonecompose.ui.theme.*
import com.ddenfi.capstonecompose.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    navToDetail: (Int) -> Unit
) {
    LaunchedEffect(key1 = false) {
        viewModel.loadNextItems()
    }

    Column(modifier.padding(horizontal = 16.dp),verticalArrangement = Arrangement.spacedBy(6.dp)) {
        HeaderSection()
        DiscoverSection(viewModel = viewModel)
        ListGamesSection(viewModel = viewModel, navToDetail = navToDetail)
    }
}


@Composable
fun HeaderSection(
    modifier: Modifier = Modifier,
) {
    HeaderComp(
        text = "Pro:game",
        delimiter = ':',
        painter = painterResource(id = R.drawable.mdi_app_notification),
        modifier = modifier.padding(vertical = 16.dp),
    )
}

@Composable
fun DiscoverSection(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
) {
    val query by viewModel.searchQuery.collectAsState()
    val focusManager = LocalFocusManager.current

    Column(
        modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Discover",
            fontFamily = lexend,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h6.fontSize,
            color = Color.White,
        )
        SearchBar(
            query = query,
            onQueryChange = viewModel::searchGame,
            onCloseSearchBar = {
                focusManager.clearFocus()
                viewModel.searchGame("")
            },
        )
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onCloseSearchBar:() -> Unit,
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = query,
        onValueChange = onQueryChange,
        label = {
            Text(
                text = "Search Your Game",
                color = TextWhite,
                fontSize = 12.sp,
                fontFamily = lexend
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = TextWhite2,
            focusedBorderColor = TextWhite,
            cursorColor = TextWhite3,
            textColor = TextWhite2
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Your Game",
                tint = TextWhite
            )
        },
        shape = Shapes.small,
        singleLine = true,
        placeholder = {
            Text(
                text = query,
                color = TextWhite,
                fontSize = 12.sp,
                fontFamily = lexend
            )
        },
        trailingIcon = {
           if (query.isNotBlank())  IconButton(onClick = onCloseSearchBar) {
               Icon(imageVector = Icons.Default.Close, contentDescription = "Close Search Bar")
           }
        }
    )
}

@Composable
fun ListGamesSection(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    navToDetail: (Int) -> Unit,
) {
    val state = viewModel.allGameState
    LazyColumn(
        modifier = modifier.padding(top = 18.dp),
    ) {
        items(state.items.size) { i ->
            val item = state.items[i]
            if (i >= state.items.size - 1 && !state.endReached && !state.isLoading) {
                viewModel.loadNextItems()
            }
            ItemGame(
                modifier = Modifier.clickable {
                    navToDetail(item.id)
                    Log.d("ITEM", item.name)},
                game = item,
            )

        }
        items(10) {
            if (state.isLoading) {
                ItemGameLoading()
            }
        }
        item {
            if (state.hasError){
                ErrorScreen(errorMessage = state.error ?: "Unknown Error Occurred!")
            }
        }
    }
}


