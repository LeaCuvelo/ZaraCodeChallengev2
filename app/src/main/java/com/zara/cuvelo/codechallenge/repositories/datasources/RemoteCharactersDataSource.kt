package com.zara.cuvelo.codechallenge.repositories.datasources

import androidx.paging.PagingData
import com.zara.cuvelo.codechallenge.domain.CharacterDomain
import kotlinx.coroutines.flow.Flow

interface RemoteCharactersDataSource {
   fun getAllCharacters(): Flow<PagingData<CharacterDomain>>
}