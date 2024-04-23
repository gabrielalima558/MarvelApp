package com.gabriela.marveltest.presentation

import androidx.lifecycle.Observer
import com.gabriela.marveltest.domain.Character
import com.gabriela.marveltest.domain.main.CharacterState
import com.gabriela.marveltest.domain.main.MarvelCharacterHandlerBusiness
import com.gabriela.marveltest.presentation.main.MarvelCharacterViewModel
import com.gabriela.marveltest.rule.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MarvelCharacterViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private var business: MarvelCharacterHandlerBusiness = mockk(relaxed = true)
    private var marvelCharacterViewModel = MarvelCharacterViewModel(business)
    private val charactersStateObserver: Observer<CharacterState> = mockk(relaxed = true)

    @Before
    fun setup() {
        marvelCharacterViewModel.charactersStateObserver.observeForever(charactersStateObserver)
    }

    @Test
    fun getMarvelCharactersState_apiReturnSuccess_shouldReturnCharacter() = runTest {
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
        verify {
            charactersStateObserver.onChanged(mockSuccessResult)
        }
    }
}