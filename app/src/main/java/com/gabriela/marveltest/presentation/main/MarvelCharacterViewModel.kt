package com.gabriela.marveltest.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriela.marveltest.domain.model.Character
import com.gabriela.marveltest.domain.main.CharacterState
import com.gabriela.marveltest.domain.main.MarvelCharacterHandlerBusiness
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MarvelCharacterViewModel(private val business: MarvelCharacterHandlerBusiness) : ViewModel() {

    private var charactersState = MutableLiveData<CharacterState>()
    val charactersStateObserver: LiveData<CharacterState> = charactersState

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            charactersState.value = business.getMarvelCharactersState()
        }
    }

    fun insertFavoriteCharacter(character: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            business.insertCharacter(character)
        }
    }
}

