package com.zara.cuvelo.codechallenge.data.networking

import retrofit2.http.GET
import retrofit2.http.Query

const val NETWORK_PAGE_SIZE = 30

interface CharacterRemoteServer {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int) : CharacterResult
}