package com.gabriela.marveltest.repository

import com.gabriela.marveltest.data.local.MarvelCharacterDatabase
import com.gabriela.marveltest.data.local.entity.MarvelCharacterEntity
import com.gabriela.marveltest.data.remote.MarvelAPI
import com.gabriela.marveltest.data.remote.Result
import com.gabriela.marveltest.data.remote.model.MarvelCharacter
import com.gabriela.marveltest.data.remote.model.MarvelCharactersResult
import com.gabriela.marveltest.data.remote.model.MarvelResult
import com.gabriela.marveltest.domain.Character
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Test


class MarvelCharacterRepositoryTest {
    private val marvelApi = mockk<MarvelAPI>(relaxed = true)
    private val marvelCharacterDatabase = mockk<MarvelCharacterDatabase>(relaxed = true)
    private val marvelCharacterRepository =
        MarvelCharacterRepositoryImpl(marvelApi, marvelCharacterDatabase)

    @Test
    fun getMarvelCharacters_whenApiReturnMarvelResults_shouldReturnMarvelCharacters() =
        runBlocking {
            //ARRAGE
            coEvery { marvelApi.getMarvelCharacters(any()) } returns MarvelResult(
                MarvelCharactersResult(
                    offset = 1, limit = 1, total = 1, count = 1, results = listOf(
                        MarvelCharacter(name = "Spider man", description = "Amigo da vizinhança")
                    )
                )
            )

            //ACT
            val result = marvelCharacterRepository.getMarvelCharacters().single()

            //ASSERT
            assertEquals(
                result,
                Result.Success(
                    listOf(
                        MarvelCharacter(
                            name = "Spider man",
                            description = "Amigo da vizinhança"
                        )
                    )
                )
            )
        }

    @Test
    fun getFavoritesList_whenDatabaseReturnsListCharacter_shouldReturnFavoriteListCharacters() =
        runBlocking {
            //ARRANGE
            val mockCharacter = Character(name = "Spider Man", description = "Amigo da vizinhança")
            coEvery { marvelCharacterDatabase.dao.listCharacter() } returns listOf(
                MarvelCharacterEntity(
                    name = "Spider Man",
                    description = "Amigo da vizinhança"
                )
            )

            //ACT
            val result = marvelCharacterRepository.getFavoritesList()

            //ASSERT
            assertEquals(
                result, listOf(mockCharacter)
            )
        }
}