package com.gabriela.marveltest.di

import androidx.room.Room
import com.gabriela.marveltest.data.local.MarvelCharacterDatabase
import com.gabriela.marveltest.data.remote.MarvelAPI
import com.gabriela.marveltest.domain.MarvelCharacterBusiness
import com.gabriela.marveltest.domain.favorite.MarvelFavoriteBusiness
import com.gabriela.marveltest.domain.main.MarvelCharacterHandlerBusiness
import com.gabriela.marveltest.presentation.main.adapter.MarvelCharacterAdapter
import com.gabriela.marveltest.presentation.favorite.MarvelFavoriteViewModel
import com.gabriela.marveltest.presentation.favorite.adapter.MarvelFavoriteAdapter
import com.gabriela.marveltest.presentation.main.MarvelCharacterViewModel
import com.gabriela.marveltest.repository.MarvelCharacterRepository
import com.gabriela.marveltest.repository.MarvelCharacterRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(androidApplication(), MarvelCharacterDatabase::class.java, "marvel.db")
            .build()
    }
    factory<MarvelCharacterRepository> {
        MarvelCharacterRepositoryImpl(
            marvelApi = MarvelAPI.getService(),
            marvelCharacterDatabase = get()
        )
    }
    single { MarvelCharacterBusiness(viewModel = get()) }
    factory { MarvelCharacterHandlerBusiness(repository = get()) }
    factory { MarvelFavoriteBusiness(repository = get()) }
    viewModel { MarvelCharacterViewModel(business = get()) }
    viewModel { MarvelFavoriteViewModel(business = get()) }
    single { MarvelCharacterAdapter(marvelCharacterBusiness = get()) }
    single { MarvelFavoriteAdapter() }
}