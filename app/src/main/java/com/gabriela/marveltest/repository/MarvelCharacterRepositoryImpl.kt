package com.gabriela.marveltest.repository

import com.gabriela.marveltest.data.MarvelAPI
import com.gabriela.marveltest.data.Result
import com.gabriela.marveltest.data.model.MarvelCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class MarvelCharacterRepositoryImpl(private val marvelApi: MarvelAPI): MarvelCharacterRepository {

    override suspend fun getMarvelCharacters(): Flow<Result<List<MarvelCharacter>>> {
        return flowOf(marvelApi.getMarvelCharacters().data.results)
            .map {
                Result.Success(it)
            }.catch { exception ->
                Result.Error(exception)
            }
    }
}