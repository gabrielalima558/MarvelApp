package com.gabriela.marveltest.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.gabriela.marveltest.databinding.CharacterItemBinding
import com.gabriela.marveltest.domain.Character

class MarvelCharacterViewHolder(private val binding: CharacterItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(character: Character) {
        binding.characterName.text = character.name
        binding.characterDescription.text = character.description
        binding.likeButton.setOnClickListener {
            if(!binding.likeButton.isSelected) {
                binding.likeButton.isSelected = true
            } else {
                binding.likeButton.isSelected = false
            }
        }
    }

}