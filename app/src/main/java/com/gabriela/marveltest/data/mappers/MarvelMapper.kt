package com.gabriela.marveltest.data.mappers

import com.gabriela.marveltest.data.model.MarvelCharacter

fun List<MarvelCharacter>?.toCharacterDomain(): List<com.gabriela.marveltest.domain.Character>? {
    return this?.map {
        com.gabriela.marveltest.domain.Character(
            name = it.name,
            description = it.description
        )
    }
}