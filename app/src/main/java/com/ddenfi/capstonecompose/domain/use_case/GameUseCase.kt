package com.ddenfi.capstonecompose.domain.use_case

import com.ddenfi.capstonecompose.domain.model.GameModel
import com.ddenfi.capstonecompose.utils.Resource
import kotlinx.coroutines.flow.Flow


interface GameUseCase {
    fun getAllGame(page: Int, pageSize: Int, searchQuery: String): Flow<Resource<List<GameModel>>>
    fun getDetailGame(gameId: Int): Flow<Resource<GameModel>>
    fun setFavoriteGame(gameId: Int, isFavorite: Boolean)
    fun getAllFavoriteGame(): Flow<Resource<List<GameModel>>>
}