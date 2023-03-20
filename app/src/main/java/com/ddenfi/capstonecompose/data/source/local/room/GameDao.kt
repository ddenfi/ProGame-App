package com.ddenfi.capstonecompose.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ddenfi.capstonecompose.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM game_entity WHERE id = :gameId ")
    fun getGame(gameId: Int): Flow<GameEntity>

    @Query("SELECT * FROM game_entity WHERE isFavorite = 1")
    fun getFavoriteGames(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = GameEntity::class)
    suspend fun insertGame(game: GameEntity)

    @Query("DELETE FROM game_entity")
    suspend fun deleteAllGame()

    @Query("UPDATE game_entity SET isFavorite = :isFavorite WHERE id = :gameId")
    suspend fun updateGame(gameId: Int, isFavorite: Boolean)
}