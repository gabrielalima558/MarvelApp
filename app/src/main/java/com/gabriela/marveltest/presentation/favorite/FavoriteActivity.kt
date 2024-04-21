package com.gabriela.marveltest.presentation.favorite

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.gabriela.marveltest.databinding.ActivityFavoriteBinding
import com.gabriela.marveltest.presentation.favorite.adapter.MarvelFavoriteAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {
    private val viewModel: MarvelFavoriteViewModel by viewModel()
    private val binding by lazy { ActivityFavoriteBinding.inflate(layoutInflater) }
    private val marvelFavoriteAdapter: MarvelFavoriteAdapter by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        buildList()
    }

    private fun buildList() {
        with(binding.recycler) {
            viewModel.charactersStateObserver.observe(context as LifecycleOwner) {
                adapter = marvelFavoriteAdapter
                if (it != null) {
                    marvelFavoriteAdapter.updateItems(it)
                }
            }
        }
    }
}