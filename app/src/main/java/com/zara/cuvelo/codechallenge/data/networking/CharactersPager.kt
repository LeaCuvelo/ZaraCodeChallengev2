package com.zara.cuvelo.codechallenge.data.networking

import android.net.Uri
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zara.cuvelo.codechallenge.data.db.entity.Character
import com.zara.cuvelo.codechallenge.data.formatAvatarUrl
import java.lang.Exception

class CharactersPager(private val characterRemoteServer: CharacterRemoteServer) : PagingSource<Int, Character>() {

    private val TAG = "CharactersPager"

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val nextPage : Int = params.key ?: 1
            val result = characterRemoteServer.getCharacters(nextPage)
            var nextPageNumber : Int? = null

            if (result.info.next != null){
                val uri = Uri.parse(result.info.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }

            val responseWithUrlMapped = result.results.map { character: Character ->
                Character(character.id, character.name, character.status, character.species, character.formatAvatarUrl(character.photoUrl), nextPage)
            }

            LoadResult.Page(data = responseWithUrlMapped, prevKey = null, nextKey = nextPageNumber)

        }catch(e: Exception){
            e.message?.let { Log.e(TAG, it) }
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

}