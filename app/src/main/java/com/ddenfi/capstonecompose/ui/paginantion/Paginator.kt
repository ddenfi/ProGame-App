package com.ddenfi.capstonecompose.ui.paginantion

interface Paginator<Key,Item> {
    suspend fun loadNextItems()
    fun reset()
}