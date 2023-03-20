package com.ddenfi.capstonecompose.data.source.local

import com.ddenfi.capstonecompose.data.source.local.entity.GameEntity
import com.ddenfi.capstonecompose.data.source.local.room.GameDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val gameDao: GameDao) {

    suspend fun insertGame(game: GameEntity) = gameDao.insertGame(game)

    suspend fun setFavoriteGame(gameId: Int, isFavorite: Boolean) {
        gameDao.updateGame(gameId, isFavorite)
    }

    fun getFavoriteGame() = gameDao.getFavoriteGames()

    fun getGameById(gameId:Int) = gameDao.getGame(gameId)

}