package com.ddenfi.capstonecompose.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddenfi.capstonecompose.domain.model.FavGameScreenState
import com.ddenfi.capstonecompose.domain.model.GameDetailScreenState
import com.ddenfi.capstonecompose.domain.model.PagingUiState
import com.ddenfi.capstonecompose.domain.use_case.GameUseCase
import com.ddenfi.capstonecompose.ui.paginantion.GamePaginator
import com.ddenfi.capstonecompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val gameUseCase: GameUseCase) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    var allGameState by mutableStateOf(PagingUiState())

    private val _favGameState = MutableStateFlow(FavGameScreenState())
    val favGameScreenState = _favGameState.asStateFlow()

    private val allGamePaginator = GamePaginator(
        initialKey = allGameState.page,
        onLoadUpdated = {
            allGameState = allGameState.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            gameUseCase.getAllGame(page = nextPage, 20, searchQuery = searchQuery.value)
        },
        getNextKey = {
            allGameState.page + 1
        },
        onError = {
            allGameState = allGameState.copy(hasError = true,error = it)
        },
        onSuccess = { items, newKey ->
            allGameState = allGameState.copy(
                items = allGameState.items + items,
                page = newKey,
                endReached = items.isEmpty(),
                hasError = false
            )
        }
    )

    fun loadNextItems() {
        viewModelScope.launch {
            allGamePaginator.loadNextItems()
        }
    }

    fun searchGame(newQuery: String) {
        _searchQuery.value = newQuery
        viewModelScope.launch {
            searchQuery.collectLatest {
                delay(300L)
                allGameState = allGameState.copy(items = listOf())
                allGamePaginator.reset()
                loadNextItems()
                Log.d("VM", "Search Game")
            }
        }
    }


    fun getAllFavGame(){
        viewModelScope.launch {
            gameUseCase.getAllFavoriteGame().onEach{ gameData ->
                when (gameData) {
                    is Resource.Success -> {
                        _favGameState.value = FavGameScreenState(
                            data = gameData.data
                        )
                    }
                    is Resource.Loading -> {
                        _favGameState.value = _favGameState.value.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Error -> {
                        _favGameState.value = _favGameState.value.copy(
                            hasError = true,
                            errorMessage = gameData.message,
                            isLoading = false
                        )
                    }
                }
            }.collect()
        }
    }

}

