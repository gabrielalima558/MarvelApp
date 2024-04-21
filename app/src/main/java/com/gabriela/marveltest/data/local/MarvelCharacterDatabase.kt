package com.gabriela.marveltest.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gabriela.marveltest.data.local.entity.MarvelCharacterEntity

@Database(
    entities = [MarvelCharacterEntity::class],
    version = 1
)
abstract class MarvelCharacterDatabase : RoomDatabase() {

    abstract val dao: MarvelCharacterDao
}