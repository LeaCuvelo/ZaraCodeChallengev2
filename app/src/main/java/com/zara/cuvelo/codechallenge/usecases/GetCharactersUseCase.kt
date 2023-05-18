package com.zara.cuvelo.codechallenge.usecases

import androidx.paging.PagingData
import com.cuvelo.data.repositories.CharacterRepository
import com.zara.cuvelo.codechallenge.domain.CharacterDomain
import kotlinx.coroutines.flow.Flow

class GetCharactersUseCase(private val characterRepository: CharacterRepository) {

    operator fun invoke(): Flow<PagingData<CharacterDomain>> = characterRepository.getAllFromApi()

}