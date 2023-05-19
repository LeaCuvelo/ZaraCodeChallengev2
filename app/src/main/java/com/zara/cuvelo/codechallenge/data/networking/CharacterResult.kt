package com.zara.cuvelo.codechallenge.data.networking
import com.zara.cuvelo.codechallenge.data.db.entity.Character

data class CharacterInfo(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String)

data class CharacterResult(
    val info: CharacterInfo,
    val results: List<Character>)