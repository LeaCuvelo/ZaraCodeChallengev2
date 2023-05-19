package com.zara.cuvelo.codechallenge.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zara.cuvelo.codechallenge.R
import com.zara.cuvelo.codechallenge.data.db.entity.Character
import com.zara.cuvelo.codechallenge.databinding.ItemCharacterBinding

class CharactersAdapter  : PagingDataAdapter<Character, CharactersAdapter.CharacterViewHolder>(DiffUtilCallBack()) {

    private lateinit var binding: ItemCharacterBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    //region ViewHolder

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.character = character
            Picasso.get()
                .load(character.photoUrl)
                .placeholder(R.drawable.ic_character_placeholder)
                .error(R.drawable.ic_error)
                .into(binding.ivRoundedPhoto)

        }
    }

    //endregion ViewHolder

    //region DiffCallback

    class DiffUtilCallBack : DiffUtil.ItemCallback<Character>(){

        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

    }

    //endregion DiffCallback

}