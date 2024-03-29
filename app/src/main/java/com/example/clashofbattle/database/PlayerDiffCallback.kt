package com.example.clashofbattle.database

import androidx.recyclerview.widget.DiffUtil
import com.example.clashofbattle.models.Player

class PlayerDiffCallback : DiffUtil.ItemCallback<Player>() {
    override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
       return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem==newItem
    }
}