package com.ddenfi.capstonecompose.data.source.remote.network

import com.ddenfi.capstonecompose.data.source.remote.response.GameDetailResponse
import com.ddenfi.capstonecompose.data.source.remote.response.GameResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getListGames(
        @Query("key") apiKey: String,
        @Query("page") page:Int,
        @Query("page_size") pageSize:Int,
        @Query("search") SearchQuery:String,
    ): GameResponse

    @GET("games/{gameId}")
    suspend fun getGameById(
        @Path("gameId") gameId: Int,
        @Query("key") apiKey: String
    ): GameDetailResponse
}