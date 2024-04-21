package com.gabriela.marveltest.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriela.marveltest.data.Result
import com.gabriela.marveltest.repository.MarvelCharacterRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.gabriela.marveltest.data.mappers.toCharacterDomain
import com.gabriela.marveltest.domain.Character

class MarvelCharacterViewModel(private val repository: MarvelCharacterRepository): ViewModel() {

    private var charactersState = MutableLiveData<List<Character>?>()
    val charactersStateObserver: LiveData<List<Character>?> = charactersState

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            repository.getMarvelCharacters().collectLatest { result ->
               when (result) {
                   is Result.Success -> {
                       val characters = result.data.toCharacterDomain()
                       charactersState.value = characters
                   }

                   is Result.Error -> {
                       val error = result.exception.localizedMessage
                       Log.e("ERROR", error.toString())
                   }
               }

            }
        }
    }
}

