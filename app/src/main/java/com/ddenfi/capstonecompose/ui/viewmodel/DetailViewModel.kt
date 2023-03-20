package com.ddenfi.capstonecompose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddenfi.capstonecompose.domain.model.GameDetailScreenState
import com.ddenfi.capstonecompose.domain.use_case.GameUseCase
import com.ddenfi.capstonecompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val gameUseCase: GameUseCase): ViewModel() {
    private val _detailGameState = MutableStateFlow(GameDetailScreenState())
    val detailGameState = _detailGameState.asStateFlow()

    fun getDetailGame(gameId:Int){
        viewModelScope.launch{
            gameUseCase.getDetailGame(gameId).onEach { gameData->
                when (gameData) {
                    is Resource.Success -> {
                        _detailGameState.value = GameDetailScreenState(
                            data = gameData.data,
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _detailGameState.value = GameDetailScreenState(
                            isLoading = true
                        )
                    }
                    is Resource.Error -> {
                        _detailGameState.value = GameDetailScreenState(
                            hasError = true,
                            errorMessage = gameData.message,
                            isLoading = false
                        )
                    }
                }
            }.collect()
        }
    }

    fun setFavGame(gameId: Int,isFavorite:Boolean) = gameUseCase.setFavoriteGame(gameId,isFavorite)

}