package com.gabriela.marveltest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gabriela.marveltest.data.local.entity.MarvelCharacterEntity

@Dao
interface MarvelCharacterDao {

    @Insert
    suspend fun insertAll(marvelCharacters: List<MarvelCharacterEntity>)

    @Insert
    suspend fun insertItem(marvelCharacters: MarvelCharacterEntity)

    @Query("SELECT * FROM marvelcharacterentity")
    fun listCharacter(): List<MarvelCharacterEntity>

    @Query("DELETE FROM marvelcharacterentity")
    suspend fun clearAll()
}