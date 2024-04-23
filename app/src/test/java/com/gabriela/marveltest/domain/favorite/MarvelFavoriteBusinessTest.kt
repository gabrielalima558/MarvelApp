package com.gabriela.marveltest.domain.favorite

import com.gabriela.marveltest.domain.Character
import com.gabriela.marveltest.repository.MarvelCharacterRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MarvelFavoriteBusinessTest {
    private val repository: MarvelCharacterRepository = mockk(relaxed = true)
    private val marvelFavoriteBusiness = MarvelFavoriteBusiness(repository)

    @Test
    fun getFavoriteCharacters_whenDatabaseReturnsFavoriteList_shouldReturnsCharactersFavoriteList() =
        runBlocking {
            //ARRANGE
            val mockCharacter = listOf(
                Character(
                    name = "Spider Man",
                    description = "O amigo da vizinhança"
                )
            )
            coEvery { repository.getFavoritesList() } returns mockCharacter

            //ACT
            val result = marvelFavoriteBusiness.getFavoriteCharacters()

            //ASSERT
            assertEquals(result, mockCharacter)
        }
}