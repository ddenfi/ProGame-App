package com.ddenfi.capstonecompose.domain.model

data class FavGameScreenState(
    val data:List<GameModel>? = emptyList(),
    val isLoading:Boolean = false,
    val hasError:Boolean = false,
    val errorMessage:String? = null,
)
