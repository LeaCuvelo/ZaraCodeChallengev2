package com.cuvelo.data.repositories

import androidx.paging.PagingData
import com.zara.cuvelo.codechallenge.domain.CharacterDomain
import com.zara.cuvelo.codechallenge.repositories.datasources.RemoteCharactersDataSource
import kotlinx.coroutines.flow.Flow

//TODO implement Local Data methods

class CharacterRepository( /*private val localCharactersDataSource: LocalCharactersDataSource,*/
                          private val remoteCharactersDataSource: RemoteCharactersDataSource
){

    fun getAllFromApi(): Flow<PagingData<CharacterDomain>> = remoteCharactersDataSource.getAllCharacters()

}