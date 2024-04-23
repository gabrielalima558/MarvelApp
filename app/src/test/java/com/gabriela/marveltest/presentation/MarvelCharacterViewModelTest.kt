package com.gabriela.marveltest.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.gabriela.marveltest.domain.Character
import com.gabriela.marveltest.domain.main.CharacterState
import com.gabriela.marveltest.domain.main.MarvelCharacterHandlerBusiness
import com.gabriela.marveltest.presentation.main.MarvelCharacterViewModel
import com.gabriela.marveltest.rule.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MarvelCharacterViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    private var business: MarvelCharacterHandlerBusiness = mockk(relaxed = true)
    private lateinit var marvelCharacterViewModel: MarvelCharacterViewModel
    private val charactersStateObserver: Observer<CharacterState> = mockk(relaxed = true)

    @Before
    fun setup() {
        marvelCharacterViewModel = MarvelCharacterViewModel(business)
        marvelCharacterViewModel.charactersStateObserver.observeForever(charactersStateObserver)
    }

    @Test
    fun getMarvelCharactersState_apiReturnSuccess_shouldReturnCharacter() {
        //ARRANGE
        val mockSuccessResult = CharacterState.Success(
            listOf(
                Character(
                    name = "Spider man",
                    description = "Amigo da vizinhança"
                )
            )
        )
        //ACT
        coEvery { business.getMarvelCharactersState() } returns mockSuccessResult

        //ASSERT
        coVerify { business.getMarvelCharactersState() }
        coVerify {
            business.getMarvelCharactersState()?.let { charactersStateObserver.onChanged(any()) }
        }
    }

    @Test
    fun insertFavoriteCharacter_whenReceiveCharacter_shouldCheckIfInsertWasCalled() {
        // ARRANGE
        val character = Character(
            name = "Spider man",
            description = "Amigo da vizinhança"
        )

        // ACT
        marvelCharacterViewModel.insertFavoriteCharacter(character)

        // ASSERT
        coVerify { business.insertCharacter(character) }
    }
}