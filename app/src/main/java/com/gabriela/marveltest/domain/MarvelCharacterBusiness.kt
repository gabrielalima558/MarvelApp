package com.gabriela.marveltest.domain

import com.gabriela.marveltest.presentation.main.MarvelCharacterViewModel

class MarvelCharacterBusiness(private val viewModel: MarvelCharacterViewModel) {
    fun addFavoriteCharacter(character: Character) {
        viewModel.insertFavoriteCharacter(character)
    }
}