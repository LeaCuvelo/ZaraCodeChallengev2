package com.zara.cuvelo.codechallenge.di

import android.content.Context
import androidx.room.Room
import com.zara.cuvelo.codechallenge.repositories.repositories.CharacterRepository
import com.zara.cuvelo.codechallenge.data.db.ZaraDatabase
import com.zara.cuvelo.codechallenge.data.db.dao.CharacterDAO
import com.zara.cuvelo.codechallenge.data.db.dao.RemoteKeysDao
import com.zara.cuvelo.codechallenge.repositories.datasources.CharactersProviderPaginator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideZaraDatabase(@ApplicationContext context: Context): ZaraDatabase =
        Room.databaseBuilder(context, ZaraDatabase::class.java, "zara_database").build()

    @Singleton
    @Provides
    fun provideCharacterDao(zaraDatabase: ZaraDatabase): CharacterDAO = zaraDatabase.getCharacterDao()

    @Singleton
    @Provides
    fun provideRemoteKeysDao(zaraDatabase: ZaraDatabase): RemoteKeysDao = zaraDatabase.getRemoteKeysDao()

    @Provides
    fun provideCharacterRepository(remoteCharactersDataSource: CharactersProviderPaginator): CharacterRepository {
        return CharacterRepository( remoteCharactersDataSource)
    }
}