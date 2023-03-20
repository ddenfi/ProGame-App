package com.ddenfi.capstonecompose.utils

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class GameTypeConverters {
    @TypeConverter
    fun listToJson(value: List<Int>) = Json.encodeToString(value)

    @TypeConverter
    fun jsonToList(value: String) = Json.decodeFromString<List<Int>>(value)
}