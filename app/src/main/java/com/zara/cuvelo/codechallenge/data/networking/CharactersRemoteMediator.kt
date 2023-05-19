package com.zara.cuvelo.codechallenge.data.networking

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.zara.cuvelo.codechallenge.data.db.ZaraDatabase
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit
import com.zara.cuvelo.codechallenge.data.db.entity.Character
import com.zara.cuvelo.codechallenge.data.db.entity.RemoteKeys
import com.zara.cuvelo.codechallenge.data.formatAvatarUrl

@OptIn(ExperimentalPagingApi::class)
class CharactersRemoteMediator (
    private val charactersApiService: CharacterRemoteServer,
    private val zaraDatabase: ZaraDatabase,
): RemoteMediator<Int, Character>() {

    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(30, TimeUnit.MINUTES)

        return if (System.currentTimeMillis() - (zaraDatabase.getRemoteKeysDao().getCreationTime() ?: 0) < cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {

            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Character>): RemoteKeys? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { movie ->
            zaraDatabase.getRemoteKeysDao().getRemoteKeyByCharacterID(movie.id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Character>): RemoteKeys? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { movie ->
            zaraDatabase.getRemoteKeysDao().getRemoteKeyByCharacterID(movie.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Character>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                zaraDatabase.getRemoteKeysDao().getRemoteKeyByCharacterID(id)
            }
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>
    ): MediatorResult {
        val page: Int = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                prevKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                nextKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }

        try {
            val apiResponse = charactersApiService.getCharacters(page = page)

            val characters = apiResponse.results
            val endOfPaginationReached = characters.isEmpty()

            zaraDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    zaraDatabase.getRemoteKeysDao().clearRemoteKeys()
                    zaraDatabase.getCharacterDao().clearAllCharacters()
                }
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (endOfPaginationReached) null else page + 1
                val remoteKeys = characters.map {
                    RemoteKeys(characterId = it.id, prevKey = prevKey, currentPage = page, nextKey = nextKey)
                }

                zaraDatabase.getRemoteKeysDao().insertAll(remoteKeys)
                zaraDatabase.getCharacterDao().insertAll(characters.onEachIndexed { _, character ->
                    character.page = page
                    character.photoUrl = character.formatAvatarUrl(character.photoUrl)
                })
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (error: IOException) {
            return MediatorResult.Error(error)
        } catch (error: HttpException) {
            return MediatorResult.Error(error)
        }
    }
}