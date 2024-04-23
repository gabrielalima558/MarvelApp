package com.gabriela.marveltest.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriela.marveltest.domain.Character
import com.gabriela.marveltest.domain.favorite.MarvelFavoriteBusiness
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MarvelFavoriteViewModel(private val business: MarvelFavoriteBusiness) : ViewModel() {

    private var charactersState = MutableLiveData<List<Character>?>()
    val charactersStateObserver: LiveData<List<Character>?> = charactersState

    init {
        getFavoriteCharacters()
    }

    private fun getFavoriteCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            charactersState.postValue(business.getFavoriteCharacters())
        }
    }
}