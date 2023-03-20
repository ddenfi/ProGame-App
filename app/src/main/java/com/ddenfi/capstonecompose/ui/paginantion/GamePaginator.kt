package com.ddenfi.capstonecompose.ui.paginantion

import android.util.Log
import com.ddenfi.capstonecompose.utils.Resource
import kotlinx.coroutines.flow.Flow

class GamePaginator<Key,Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Flow<Resource<List<Item>>>,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onError: suspend (String?) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
):Paginator<Key,Item> {
    private var currentKey = initialKey
    private var isMakingRequest = false
    private var items = listOf<Item>()

    override suspend fun loadNextItems() {
        if (isMakingRequest) {
            return
        }
        val result = onRequest(currentKey)
        result.collect { data ->
            when (data) {
                is Resource.Loading -> {
                    onLoadUpdated(true)
                    isMakingRequest = true
                }
                is Resource.Success -> {
                    items = data.data!!
                    onLoadUpdated(false)
                    isMakingRequest = false
                }
                is Resource.Error -> {
                    onLoadUpdated(false)
                    isMakingRequest = false
                    onError(data.message)
                    Log.d("REQUEST","Error ${data.message}")
                }
            }
        }

        currentKey = getNextKey(items)
        onSuccess(items, currentKey)
        onLoadUpdated(false)
    }

    override fun reset() {
        currentKey = initialKey
        items = listOf()
    }
}