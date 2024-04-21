package com.gabriela.marveltest.presentation.favorite.adapter

import androidx.recyclerview.widget.RecyclerView
import com.gabriela.marveltest.databinding.CharacterItemBinding
import com.gabriela.marveltest.databinding.FavoriteCharacterItemBinding
import com.gabriela.marveltest.domain.Character

class MarvelFavoriterViewHolder(
    private val binding: FavoriteCharacterItemBinding,
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(character: Character) {
        binding.characterName.text = character.name
        binding.characterDescription.text = character.description
    }
}