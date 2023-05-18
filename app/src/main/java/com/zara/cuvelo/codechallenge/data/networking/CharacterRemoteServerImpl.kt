package com.zara.cuvelo.codechallenge.data.networking

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zara.cuvelo.codechallenge.domain.CharacterDomain
import com.zara.cuvelo.codechallenge.repositories.datasources.RemoteCharactersDataSource
import kotlinx.coroutines.flow.Flow

class CharacterRemoteServerImpl(private val characterRemoteServer: CharacterRemoteServer) :
    RemoteCharactersDataSource {

    override fun getAllCharacters(): Flow<PagingData<CharacterDomain>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                CharactersPager(characterRemoteServer)
            }
            , initialKey = 1
        ).flow

    }
}