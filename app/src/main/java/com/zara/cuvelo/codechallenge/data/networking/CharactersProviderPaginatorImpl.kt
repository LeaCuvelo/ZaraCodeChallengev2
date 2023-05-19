package com.zara.cuvelo.codechallenge.data.networking

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zara.cuvelo.codechallenge.data.db.ZaraDatabase
import com.zara.cuvelo.codechallenge.data.db.entity.Character
import com.zara.cuvelo.codechallenge.repositories.datasources.CharactersProviderPaginator
import kotlinx.coroutines.flow.Flow

class CharactersProviderPaginatorImpl(private val characterRemoteServer: CharacterRemoteServer, private val zaraDatabase: ZaraDatabase) :
    CharactersProviderPaginator {

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
               zaraDatabase.getCharacterDao().getCharacters()
            }, remoteMediator = CharactersRemoteMediator(characterRemoteServer,zaraDatabase)
            , initialKey = 1
        ).flow
    }
}