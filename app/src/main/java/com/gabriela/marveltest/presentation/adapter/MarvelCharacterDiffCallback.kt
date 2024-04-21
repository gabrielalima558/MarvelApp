package com.gabriela.marveltest.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.gabriela.marveltest.domain.Character

class MarvelCharacterDiffCallback(
    private val oldList: List<Character>,
    private val newList: List<Character>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].name == newList[newItemPosition].name

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}