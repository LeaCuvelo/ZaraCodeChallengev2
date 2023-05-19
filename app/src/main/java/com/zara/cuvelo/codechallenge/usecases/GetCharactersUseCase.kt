package com.zara.cuvelo.codechallenge.usecases

import androidx.paging.PagingData
import com.zara.cuvelo.codechallenge.data.db.entity.Character
import com.zara.cuvelo.codechallenge.repositories.repositories.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetCharactersUseCase(private val characterRepository: CharacterRepository) {

    operator fun invoke(): Flow<PagingData<Character>> = characterRepository.getAllFromApi()

}