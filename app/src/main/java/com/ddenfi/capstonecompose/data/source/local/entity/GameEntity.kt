package com.ddenfi.capstonecompose.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_entity")
data class GameEntity(
    @PrimaryKey
    val id:Int,
    val name:String?,
    val rating:Double?,
    val reviewsCount:Int?,
    val backgroundImage:String?,
    val description:String?,
    val parentPlatform:List<Int>?,
    val releasedDate:String?,
    val isFavorite:Boolean = false
)
