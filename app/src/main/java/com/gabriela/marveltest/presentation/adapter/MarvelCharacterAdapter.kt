package com.gabriela.marveltest.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gabriela.marveltest.databinding.CharacterItemBinding
import com.gabriela.marveltest.domain.Character

class MarvelCharacterAdapter: RecyclerView.Adapter<MarvelCharacterViewHolder>() {
    private var items: List<Character> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelCharacterViewHolder {
        val binding = CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarvelCharacterViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: MarvelCharacterViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    fun updateItems(newItems: List<Character>) {
        val diffUtil = DiffUtil.calculateDiff(MarvelCharacterDiffCallback(items, newItems))
        items = newItems
        diffUtil.dispatchUpdatesTo(this)
    }
}