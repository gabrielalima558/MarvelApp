package com.gabriela.marveltest.presentation.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.gabriela.marveltest.databinding.CharacterItemBinding
import com.gabriela.marveltest.domain.model.Character
import com.gabriela.marveltest.presentation.main.MarvelCharacterViewModel

class MarvelCharacterViewHolder(
    private val binding: CharacterItemBinding,
    private val viewModel: MarvelCharacterViewModel,
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(character: Character) {
        binding.characterName.text = character.name
        binding.characterDescription.text = character.description
        binding.likeButton.setOnClickListener {
            if (!binding.likeButton.isSelected) {
                binding.likeButton.isSelected = true
                viewModel.insertFavoriteCharacter(character)
            } else {
                binding.likeButton.isSelected = false
            }
        }
    }

}