package com.gabriela.marveltest.data.remote.model

data class MarvelCharactersResult (
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<MarvelCharacter>,
)