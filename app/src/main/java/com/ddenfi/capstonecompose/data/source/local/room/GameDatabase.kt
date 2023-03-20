package com.ddenfi.capstonecompose.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ddenfi.capstonecompose.data.source.local.entity.GameEntity
import com.ddenfi.capstonecompose.utils.GameTypeConverters

@Database(
    entities = [GameEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(GameTypeConverters::class)
abstract class GameDatabase:RoomDatabase() {
    abstract fun gameDao():GameDao
}