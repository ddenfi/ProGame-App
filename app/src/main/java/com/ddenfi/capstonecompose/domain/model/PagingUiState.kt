package com.ddenfi.capstonecompose.domain.model

data class PagingUiState(
    val isLoading: Boolean = false,
    val items: List<GameModel> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 1,
    val hasError:Boolean = false
)