package com.ddenfi.capstonecompose.data.repository

import com.ddenfi.capstonecompose.data.source.NetworkBoundResource
import com.ddenfi.capstonecompose.data.source.local.LocalDataSource
import com.ddenfi.capstonecompose.data.source.remote.RemoteDataSource
import com.ddenfi.capstonecompose.data.source.remote.network.ApiResponse
import com.ddenfi.capstonecompose.data.source.remote.response.GameDetailResponse
import com.ddenfi.capstonecompose.domain.model.GameModel
import com.ddenfi.capstonecompose.domain.repository.IGameRepository
import com.ddenfi.capstonecompose.utils.DataMapper
import com.ddenfi.capstonecompose.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IGameRepository {
    override fun getAllGame(
        page: Int,
        pageSize: Int,
        searchQuery: String
    ): Flow<Resource<List<GameModel>>> {
        return flow {
            emit(Resource.Loading())
            remoteDataSource.getAllGames(page, pageSize, searchQuery).collect { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        emit(Resource.Success(apiResponse.data.map {
                            DataMapper.mapApiResponseToGameModel(it)
                        }))
                    }
                    is ApiResponse.Error -> emit(Resource.Error(apiResponse.error.message.toString()))
                    is ApiResponse.Empty -> emit(Resource.Error("Data is empty"))
                }
            }
        }
    }

    override fun getGameDetailByID(gameId: Int): Flow<Resource<GameModel>> =
        object : NetworkBoundResource<GameModel, GameDetailResponse>() {
            override fun loadFromDB(): Flow<GameModel?> {
                return localDataSource.getGameById(gameId)
                    .map { DataMapper.mapGameEntityToGameModel(it) }
            }

            override fun shouldFetch(data: GameModel?): Boolean = data?.id == 0 || data == null

            override suspend fun createCall(): Flow<ApiResponse<GameDetailResponse>> {
                return remoteDataSource.getGameById(gameId)
            }

            override suspend fun saveCallResult(data: GameDetailResponse) {
                localDataSource.insertGame(DataMapper.mapApiResponseToGameEntity(data))
            }

        }.asFlow()

    override fun setFavoriteGame(gameId: Int, isFavorite: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.setFavoriteGame(gameId, isFavorite)
        }
    }

    override fun getAllFavoriteGame(): Flow<Resource<List<GameModel>>> {
        return flow {
            emit(Resource.Loading())
            val data = localDataSource.getFavoriteGame()

            data.onEach { list ->
                try {
                    emit(Resource.Success(list.map { DataMapper.mapGameEntityToGameModel(it) }))
                } catch (e: IOException) {
                    emit((Resource.Error(e.toString())))
                }
            }.collect()
        }
    }
}