package com.ddenfi.capstonecompose.utils

import com.ddenfi.capstonecompose.R
import com.ddenfi.capstonecompose.data.source.local.entity.GameEntity
import com.ddenfi.capstonecompose.data.source.remote.response.GameDetailResponse
import com.ddenfi.capstonecompose.data.source.remote.response.Platform
import com.ddenfi.capstonecompose.data.source.remote.response.ResultsItem
import com.ddenfi.capstonecompose.domain.model.GameModel
import com.ddenfi.capstonecompose.domain.model.PlatformModel
import com.ddenfi.capstonecompose.ui.components.PlatformsItem

object DataMapper {
    fun mapApiResponseToGameModel(data: ResultsItem?): GameModel {
        return GameModel(
            id = data?.id ?: 0,
            name = data?.name ?: "",
            description = "",
            rating = data?.rating ?: 0.0,
            reviewsCount = data?.reviewsCount ?: 0,
            backgroundImage = data?.backgroundImage ?: "",
            releasedDate = data?.released ?: "",
            parentPlatform = data?.parentPlatforms?.map { mapToPlatformModel(it?.platform).id }
                ?: listOf(),
            isFavorite = false
        )
    }

    fun mapApiResponseToGameEntity(data: GameDetailResponse): GameEntity {
        return GameEntity(
            id = data.id ?: 0,
            name = data.name ?: "",
            description = data.description ?: "",
            rating = data.rating ?: 0.0,
            releasedDate = data.released ?: "",
            reviewsCount = data.reviewsCount ?: 0,
            parentPlatform = data.parentPlatforms?.map { mapToPlatformModel(it?.platform).id }
                ?: listOf(),
            backgroundImage = data.backgroundImage ?: ""
        )
    }

    fun mapGameEntityToGameModel(data: GameEntity?): GameModel {
        return GameModel(
            id = data?.id ?: 0,
            name = data?.name ?: "",
            description = data?.description ?: "",
            rating = data?.rating ?: 0.0,
            reviewsCount = data?.reviewsCount ?: 0,
            backgroundImage = data?.backgroundImage ?: "",
            releasedDate = data?.releasedDate ?: "",
            parentPlatform = data?.parentPlatform ?: listOf(),
            isFavorite = data?.isFavorite ?: false
        )
    }

    fun parentPlatformsToPlatformItem(data: Int): PlatformsItem {
        return when (data) {
            1 -> PlatformsItem(
                1,
                "Pc",
                R.drawable.ic_pc
            )
            2 -> PlatformsItem(
                2,
                "Playstation",
                R.drawable.ic_ps
            )
            8 -> PlatformsItem(
                8,
                "Android",
                R.drawable.ic_android
            )
            4 -> PlatformsItem(
                4,
                "ios",
                R.drawable.ic_ios
            )
            7 -> PlatformsItem(
                7,
                "Nintendo",
                R.drawable.ic_nintendo
            )
            3 -> PlatformsItem(
                3,
                "Xbox",
                R.drawable.ic_xbox
            )
            else -> PlatformsItem(
                0,
                "Other Platform",
                R.drawable.ic_game
            )
        }
    }

    private fun mapToPlatformModel(data: Platform?): PlatformModel {
        return PlatformModel(
            id = data?.id ?: 0
        )
    }
}