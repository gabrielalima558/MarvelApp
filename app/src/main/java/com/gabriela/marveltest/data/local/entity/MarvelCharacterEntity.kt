package com.gabriela.marveltest.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MarvelCharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
)