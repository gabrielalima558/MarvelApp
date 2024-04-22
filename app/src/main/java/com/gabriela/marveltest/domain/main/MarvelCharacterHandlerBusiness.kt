package com.gabriela.marveltest.domain.main

import android.util.Log
import com.gabriela.marveltest.data.mappers.toCharacterDomain
import com.gabriela.marveltest.data.remote.Result
import com.gabriela.marveltest.domain.Character
import com.gabriela.marveltest.repository.MarvelCharacterRepository
import kotlinx.coroutines.flow.collectLatest

class MarvelCharacterHandlerBusiness(private val repository: MarvelCharacterRepository) {
    suspend fun getMarvelCharactersState(): CharacterState? {
        var characterState: CharacterState? = null
         repository.getMarvelCharacters().collectLatest { result ->
             characterState = when (result) {
                 is Result.Success -> {
                     val characters = result.data.toCharacterDomain()
                     CharacterState.Success(characters)
                 }

                 is Result.Error -> {
                     val error = result.exception.localizedMessage
                     Log.e("ERROR", error.toString())
                     CharacterState.Error
                 }
             }
        }
        return characterState
    }

    suspend fun insertCharacter(character: Character) {
        repository.setMarvelInfo(character)
    }
}