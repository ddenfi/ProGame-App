package com.ddenfi.capstonecompose.domain.model

data class GameDetailScreenState(
    val data:GameModel? = null,
    val isLoading:Boolean = false,
    val hasError:Boolean = false,
    val errorMessage:String? = null,
)
