package com.gabriela.marveltest.data.mappers

import com.gabriela.marveltest.data.local.entity.MarvelCharacterEntity
import com.gabriela.marveltest.data.remote.model.MarvelCharacter
import com.gabriela.marveltest.domain.model.Character

fun List<MarvelCharacter>?.toCharacterDomain(): List<Character>? {
    return this?.map {
        Character(
            name = it.name,
            description = it.description
        )
    }
}

fun Character?.toCharacter(): MarvelCharacterEntity? {
    return this?.let { MarvelCharacterEntity(name = it.name, description = this.description) }
}

fun List<MarvelCharacterEntity>.toCharacterList(): List<Character>? {
    return this?.map {
        Character(
            name = it.name,
            description = it.description
        )
    }
}