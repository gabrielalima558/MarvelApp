package com.gabriela.marveltest.domain

import com.gabriela.marveltest.data.remote.model.MarvelCharacter

data class Characters (
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<MarvelCharacter>,
)