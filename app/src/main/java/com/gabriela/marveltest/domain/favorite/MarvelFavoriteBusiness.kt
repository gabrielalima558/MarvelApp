package com.gabriela.marveltest.domain.favorite

import com.gabriela.marveltest.domain.model.Character
import com.gabriela.marveltest.repository.MarvelCharacterRepository

class MarvelFavoriteBusiness(private val repository: MarvelCharacterRepository) {

    suspend fun getFavoriteCharacters(): List<Character>? = repository.getFavoritesList()

}