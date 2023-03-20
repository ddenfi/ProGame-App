package com.ddenfi.capstonecompose.domain.use_case

import com.ddenfi.capstonecompose.data.repository.GameRepository
import com.ddenfi.capstonecompose.domain.model.GameModel
import com.ddenfi.capstonecompose.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameInteractor @Inject constructor(private val gameRepository: GameRepository):GameUseCase {
    override fun getAllGame(
        page: Int,
        pageSize: Int,
        searchQuery: String
    ): Flow<Resource<List<GameModel>>> {
        return gameRepository.getAllGame(page,pageSize,searchQuery)
    }

    override fun getDetailGame(gameId: Int): Flow<Resource<GameModel>> = gameRepository.getGameDetailByID(gameId)

    override fun setFavoriteGame(gameId: Int, isFavorite: Boolean) = gameRepository.setFavoriteGame(gameId,isFavorite)

    override fun getAllFavoriteGame(): Flow<Resource<List<GameModel>>> = gameRepository.getAllFavoriteGame()

}