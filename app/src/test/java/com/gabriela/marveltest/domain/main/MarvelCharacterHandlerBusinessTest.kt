package com.gabriela.marveltest.domain.main

import com.gabriela.marveltest.data.remote.Result
import com.gabriela.marveltest.data.remote.model.MarvelCharacter
import com.gabriela.marveltest.domain.Character
import com.gabriela.marveltest.repository.MarvelCharacterRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MarvelCharacterHandlerBusinessTest {
    private val repository: MarvelCharacterRepository = mockk(relaxed = true)
    private val marvelCharacterHandlerBusiness = MarvelCharacterHandlerBusiness(repository)

    @Test
    fun getMarvelCharactersState_whenRepositoryReturnsSuccess_shouldReturnSuccess() = runBlocking {
        //ARRAGE
        val data =
            listOf(MarvelCharacter(name = "Spider Man", description = "O amigo da vizinhança"))
        coEvery { repository.getMarvelCharacters() } returns flowOf(Result.Success(data))

        //ACT
        val result = marvelCharacterHandlerBusiness.getMarvelCharactersState()

        //ASSERT
        assertEquals(
            result,
            CharacterState.Success(
                listOf(
                    Character(
                        name = "Spider Man",
                        description = "O amigo da vizinhança"
                    )
                )
            )
        )
    }

    @Test
    fun getMarvelCharactersState_whenRepositoryReturnsError_shouldReturnError() = runBlocking {
        //ARRAGE
        coEvery { repository.getMarvelCharacters() } returns flowOf(Result.Error(Exception()))

        //ACT
        val result = marvelCharacterHandlerBusiness.getMarvelCharactersState()

        //ASSERT
        assertEquals(
            result,
            CharacterState.Error
        )
    }
}