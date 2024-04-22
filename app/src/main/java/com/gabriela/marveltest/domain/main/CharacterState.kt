package com.gabriela.marveltest.domain.main

import com.gabriela.marveltest.domain.Character

sealed class CharacterState {
    data class Success(var character: List<Character>?) : CharacterState()
    data object Error : CharacterState()
}