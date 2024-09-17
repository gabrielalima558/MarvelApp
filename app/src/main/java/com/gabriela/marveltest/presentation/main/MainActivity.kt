package com.gabriela.marveltest.presentation.main

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.gabriela.marveltest.databinding.ActivityMainBinding
import com.gabriela.marveltest.domain.model.Character
import com.gabriela.marveltest.domain.main.CharacterState
import com.gabriela.marveltest.presentation.main.adapter.MarvelCharacterAdapter
import com.gabriela.marveltest.presentation.favorite.FavoriteActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MarvelCharacterViewModel by viewModel()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val marvelCharacterAdapter: MarvelCharacterAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        buildList()
        buildSearcher()
        openFavorites()
    }

    private fun openFavorites() {
        binding.favoritesButton.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun buildList(filteredText: String = String()) =
        with(binding.recycler) {
            viewModel.charactersStateObserver.observe(context as LifecycleOwner) { listCharacter ->
                adapter = marvelCharacterAdapter
                when(listCharacter) {
                    is CharacterState.Success -> listCharacter.character?.let { list ->
                        crateListByFilter(filteredText, list)
                    }
                    is CharacterState.Error -> {}
                }
            }
        }

    private fun crateListByFilter(filteredText: String = String(), list: List<Character>) {
        if (filteredText.isNotEmpty()) {
            val filteredList = list.filter { character ->
                character.name.contains(filteredText, ignoreCase = true)
            }
            marvelCharacterAdapter.updateItems(filteredList)
        } else {
            marvelCharacterAdapter.updateItems(list)
        }
    }

    private fun buildSearcher() {
        binding.serchItem.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(text: Editable?) {
                buildList(text.toString())
            }
        })
    }
}