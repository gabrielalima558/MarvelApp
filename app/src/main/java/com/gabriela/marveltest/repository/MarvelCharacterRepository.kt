package com.gabriela.marveltest.repository

import com.gabriela.marveltest.data.model.MarvelCharacter
import kotlinx.coroutines.flow.Flow
import com.gabriela.marveltest.data.Result


interface MarvelCharacterRepository {

    suspend fun getMarvelCharacters(): Flow<Result<List<MarvelCharacter>>>
}