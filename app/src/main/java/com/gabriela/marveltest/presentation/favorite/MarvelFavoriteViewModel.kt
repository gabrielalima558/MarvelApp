package com.gabriela.marveltest.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabriela.marveltest.domain.Character
import com.gabriela.marveltest.repository.MarvelCharacterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MarvelFavoriteViewModel(private val repository: MarvelCharacterRepository): ViewModel() {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var charactersState = MutableLiveData<List<Character>?>()
    val charactersStateObserver: LiveData<List<Character>?> = charactersState

    init {
        getFavoriteCharacters()
    }

    private fun getFavoriteCharacters() {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                charactersState.postValue(repository.getFavoritesList())
            }
        }
    }
}