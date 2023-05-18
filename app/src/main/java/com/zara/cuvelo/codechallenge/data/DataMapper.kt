package com.zara.cuvelo.codechallenge.data

import com.zara.cuvelo.codechallenge.data.db.entity.CharacterEntity
import com.zara.cuvelo.codechallenge.data.model.CharacterModel
import com.zara.cuvelo.codechallenge.domain.CharacterDomain


fun CharacterEntity.toCharacterDomain() = CharacterDomain(id,name,status,species,photoUrl)

fun CharacterDomain.toCharacterEntity(page: Int) = CharacterEntity(id,name,status,species,photo,page)  //TODO page no hace falta creo

fun CharacterModel.toCharacterDomain() = CharacterDomain(id,name,status,species,photo)

fun CharacterModel.toCharacterEntity(page: Int) = CharacterEntity(id,name,status,species,photo,page)

fun CharacterModel.formatAvatarUrl(urlToFormat: String): String {
    return urlToFormat.replace(
        "https://rickandmortyapi.com/api/character/([0-9]+)".toRegex(),
        "https://rickandmortyapi.com/api/character/avatar/$1.jpeg"
    )
}

