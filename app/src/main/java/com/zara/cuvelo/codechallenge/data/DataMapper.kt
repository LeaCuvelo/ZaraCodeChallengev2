package com.zara.cuvelo.codechallenge.data

import com.zara.cuvelo.codechallenge.data.db.entity.Character

fun Character.formatAvatarUrl(urlToFormat: String): String {
    return urlToFormat.replace(
        "https://rickandmortyapi.com/api/character/([0-9]+)".toRegex(),
        "https://rickandmortyapi.com/api/character/avatar/$1.jpeg"
    )
}

