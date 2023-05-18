package com.zara.cuvelo.codechallenge.data.networking

import android.net.Uri
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.zara.cuvelo.codechallenge.data.formatAvatarUrl
import com.zara.cuvelo.codechallenge.data.model.CharacterModel
import com.zara.cuvelo.codechallenge.domain.CharacterDomain
import java.lang.Exception

class CharactersPager(private val characterRemoteServer: CharacterRemoteServer) : PagingSource<Int, CharacterDomain>() {

    private val TAG = "CharactersPager"

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDomain> {
        return try {
            val nextPage : Int = params.key ?: 1
            val result = characterRemoteServer.getCharacters(nextPage)
            var nextPageNumber : Int? = null

            if (result.info.next != null){
                val uri = Uri.parse(result.info.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }

            val responseMapped = result.results.map{ character: CharacterModel ->
                CharacterDomain(character.id, character.name, character.status, character.species, character.formatAvatarUrl(character.photo))
            }
            LoadResult.Page(data = responseMapped, prevKey = null, nextKey = nextPageNumber)

        }catch(e: Exception){
            e.message?.let { Log.e(TAG, it) }
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterDomain>): Int? {
        return state.anchorPosition
    }

}