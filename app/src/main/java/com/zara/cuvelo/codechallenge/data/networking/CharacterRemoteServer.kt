package com.zara.cuvelo.codechallenge.data.networking

import com.zara.cuvelo.codechallenge.data.model.CharacterResult
import retrofit2.http.GET
import retrofit2.http.Query

const val NETWORK_PAGE_SIZE = 30

interface CharacterRemoteServer {

    @GET("character")
    suspend fun getCharacters(@Query("page") query: Int) : CharacterResult

}