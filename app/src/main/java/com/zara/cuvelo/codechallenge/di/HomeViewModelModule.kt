package com.zara.cuvelo.codechallenge.di

import com.cuvelo.data.repositories.CharacterRepository
import com.zara.cuvelo.codechallenge.usecases.GetCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class HomeViewModelModule {

    @Provides
    fun provideGetCharactersUseCase(repository: CharacterRepository): GetCharactersUseCase {
        return GetCharactersUseCase(repository)
    }

}