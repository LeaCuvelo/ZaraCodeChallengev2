package com.zara.cuvelo.codechallenge.di

import com.zara.cuvelo.codechallenge.data.networking.CharacterRemoteServer
import com.zara.cuvelo.codechallenge.data.networking.CharacterRemoteServerImpl
import com.zara.cuvelo.codechallenge.repositories.datasources.RemoteCharactersDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideBaseUrl(): String = "https://rickandmortyapi.com/api/"

    @Provides
    @Singleton
    fun provideRetrofit(@Named("baseUrl") baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideRemoteDateService(retrofit: Retrofit): CharacterRemoteServer =
        retrofit.create(CharacterRemoteServer::class.java)

    @Provides
    fun provideRemoteDataSource(characterRemoteServer: CharacterRemoteServer ): RemoteCharactersDataSource {
        return CharacterRemoteServerImpl(characterRemoteServer)
    }

}