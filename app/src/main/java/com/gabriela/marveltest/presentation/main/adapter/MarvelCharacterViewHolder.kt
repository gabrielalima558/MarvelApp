package com.gabriela.marveltest.presentation.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.gabriela.marveltest.databinding.CharacterItemBinding
import com.gabriela.marveltest.domain.Character
import com.gabriela.marveltest.domain.MarvelCharacterBusiness

class MarvelCharacterViewHolder(
    private val binding: CharacterItemBinding,
    private val marvelCharacterBusiness: MarvelCharacterBusiness
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(character: Character) {
        binding.characterName.text = character.name
        binding.characterDescription.text = character.description
        binding.likeButton.setOnClickListener {
            if (!binding.likeButton.isSelected) {
                binding.likeButton.isSelected = true
                marvelCharacterBusiness.addFavoriteCharacter(character)
            } else {
                binding.likeButton.isSelected = false
            }
        }
    }

}