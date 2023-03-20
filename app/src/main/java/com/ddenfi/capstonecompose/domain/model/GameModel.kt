package com.ddenfi.capstonecompose.domain.model

data class GameModel(
    val id:Int,
    val name:String,
    val rating:Double,
    val reviewsCount:Int,
    val backgroundImage:String,
    val description:String,
    val parentPlatform:List<Int>,
    val releasedDate:String,
    val isFavorite:Boolean
)
