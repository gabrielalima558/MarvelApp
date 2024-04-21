package com.gabriela.marveltest.repository

import com.gabriela.marveltest.data.remote.model.MarvelCharacter
import kotlinx.coroutines.flow.Flow
import com.gabriela.marveltest.data.remote.Result
import com.gabriela.marveltest.domain.Character


interface MarvelCharacterRepository {

    suspend fun getMarvelCharacters(): Flow<Result<List<MarvelCharacter>>>

    suspend fun setMarvelInfo(character: Character)

    suspend fun getFavoritesList(): List<Character>?
}