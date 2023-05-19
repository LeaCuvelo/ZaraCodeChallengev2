package com.zara.cuvelo.codechallenge.repositories.datasources

import androidx.paging.PagingData
import com.zara.cuvelo.codechallenge.data.db.entity.Character
import kotlinx.coroutines.flow.Flow

interface CharactersProviderPaginator {
   fun getAllCharacters(): Flow<PagingData<Character>>
}