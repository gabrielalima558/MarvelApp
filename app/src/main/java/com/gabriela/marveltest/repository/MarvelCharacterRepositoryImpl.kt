package com.gabriela.marveltest.repository

import com.gabriela.marveltest.data.local.MarvelCharacterDatabase
import com.gabriela.marveltest.data.mappers.toCharacter
import com.gabriela.marveltest.data.mappers.toCharacterList
import com.gabriela.marveltest.data.remote.MarvelAPI
import com.gabriela.marveltest.data.remote.Result
import com.gabriela.marveltest.data.remote.model.MarvelCharacter
import com.gabriela.marveltest.domain.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class MarvelCharacterRepositoryImpl(
    private val marvelApi: MarvelAPI,
    private val marvelCharacterDatabase: MarvelCharacterDatabase
) : MarvelCharacterRepository {

    override suspend fun getMarvelCharacters(): Flow<Result<List<MarvelCharacter>>> {
        return flowOf(marvelApi.getMarvelCharacters().data.results)
            .map {
                Result.Success(it)
            }.catch { exception ->
                Result.Error(exception)
            }
    }

    override suspend fun setMarvelInfo(character: Character) {
        character.toCharacter()?.let { marvelCharacterDatabase.dao.insertItem(it) }
    }

    override suspend fun getFavoritesList(): List<Character>? =
        marvelCharacterDatabase.dao.listCharacter().toCharacterList()


}