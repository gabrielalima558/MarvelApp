package com.gabriela.marveltest.presentation.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gabriela.marveltest.databinding.FavoriteCharacterItemBinding
import com.gabriela.marveltest.domain.model.Character

class MarvelFavoriteAdapter : RecyclerView.Adapter<MarvelFavoriterViewHolder>() {
    private var items: List<Character> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelFavoriterViewHolder {
        val binding =
            FavoriteCharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarvelFavoriterViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: MarvelFavoriterViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    fun updateItems(newItems: List<Character>) {
        val diffUtil = DiffUtil.calculateDiff(MarvelFavoritesDiffCallback(items, newItems))
        items = newItems
        diffUtil.dispatchUpdatesTo(this)
    }
}