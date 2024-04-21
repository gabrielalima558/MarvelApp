package com.gabriela.marveltest.di

import com.gabriela.marveltest.data.MarvelAPI
import com.gabriela.marveltest.presentation.MarvelCharacterViewModel
import com.gabriela.marveltest.presentation.adapter.MarvelCharacterAdapter
import com.gabriela.marveltest.repository.MarvelCharacterRepository
import com.gabriela.marveltest.repository.MarvelCharacterRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory<MarvelCharacterRepository> { MarvelCharacterRepositoryImpl(MarvelAPI.getService()) }
    single { MarvelCharacterAdapter() }
    viewModel { MarvelCharacterViewModel(repository = get()) }
}