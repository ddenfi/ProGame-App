package com.ddenfi.capstonecompose.domain.repository

import com.ddenfi.capstonecompose.domain.model.GameModel
import com.ddenfi.capstonecompose.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getAllGame(page:Int,pageSize:Int,searchQuery:String): Flow<Resource<List<GameModel>>>

    fun getGameDetailByID(gameId: Int): Flow<Resource<GameModel>>

    fun setFavoriteGame(gameId: Int, isFavorite: Boolean)

    fun getAllFavoriteGame(): Flow<Resource<List<GameModel>>>
}