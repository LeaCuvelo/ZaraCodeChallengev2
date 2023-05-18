package com.zara.cuvelo.codechallenge.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zara.cuvelo.codechallenge.R
import com.zara.cuvelo.codechallenge.databinding.ItemCharacterBinding
import com.zara.cuvelo.codechallenge.domain.CharacterDomain

class CharactersAdapter  : PagingDataAdapter<CharacterDomain, CharactersAdapter.CharacterViewHolder>(DiffUtilCallBack()) {

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

        fun bind(character: CharacterDomain) {
            binding.character = character

            //https://rickandmortyapi.com/api/character/avatar/1.jpeg
            Picasso.get()
                .load(character.photo)
                .placeholder(R.drawable.ic_character_placeholder)
                .error(R.drawable.ic_error)
                .into(binding.ivRoundedPhoto)

        }
    }

    //endregion ViewHolder

    //region DiffCallback

    class DiffUtilCallBack : DiffUtil.ItemCallback<CharacterDomain>(){

        override fun areItemsTheSame(oldItem: CharacterDomain, newItem: CharacterDomain): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CharacterDomain, newItem: CharacterDomain): Boolean {
            return oldItem == newItem
        }

    }

    //endregion DiffCallback

}