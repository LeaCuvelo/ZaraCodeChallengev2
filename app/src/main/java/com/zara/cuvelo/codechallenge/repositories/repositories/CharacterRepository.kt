package com.zara.cuvelo.codechallenge.repositories.repositories

import androidx.paging.PagingData
import com.zara.cuvelo.codechallenge.data.db.entity.Character
import com.zara.cuvelo.codechallenge.repositories.datasources.CharactersProviderPaginator
import kotlinx.coroutines.flow.Flow


class CharacterRepository(private val remoteCharactersDataSource: CharactersProviderPaginator){

    fun getAllFromApi(): Flow<PagingData<Character>> = remoteCharactersDataSource.getAllCharacters()

}