package com.ddenfi.capstonecompose.data.source.remote

import android.util.Log
import com.ddenfi.capstonecompose.data.source.remote.network.ApiResponse
import com.ddenfi.capstonecompose.data.source.remote.network.ApiService
import com.ddenfi.capstonecompose.data.source.remote.response.GameDetailResponse
import com.ddenfi.capstonecompose.data.source.remote.response.ResultsItem
import com.ddenfi.capstonecompose.utils.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllGames(page:Int = 1,pageSize:Int = 10, searchQuery:String): Flow<ApiResponse<List<ResultsItem?>>> {
        return flow {
            try {
                val response = apiService.getListGames(API_KEY,page,pageSize,searchQuery)
                val dataArray = response.results
                if (dataArray!!.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: HttpException) {
                emit(ApiResponse.Error(e))
                Log.e("Remote Data Source", e.toString())
            } catch (e: IOException) {
                emit(ApiResponse.Error(e))
                Log.e("Remote Data Source", "Check Connection")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGameById(gameId: Int): Flow<ApiResponse<GameDetailResponse>> = flow {
        Log.d("Remote Data Source","called by id")
        try {
            val response = apiService.getGameById(gameId, API_KEY)
            emit(ApiResponse.Success(response))
            response.name?.let { Log.d("Remote Data Source", it) }
        } catch (e: HttpException) {
            emit(ApiResponse.Error(e))
            Log.e("Remote Data Source", e.toString())
        } catch (e: IOException) {
            emit(ApiResponse.Error(e))
            Log.e("Remote Data Source", "Check Connection")
        }
    }
}