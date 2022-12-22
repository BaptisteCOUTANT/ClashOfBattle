package com.example.clashofbattle.database

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.clashofbattle.databinding.FragmentHorizontalListeJoueurBinding
import com.example.clashofbattle.models.Capability
import com.example.clashofbattle.models.CapabilityType
import com.example.clashofbattle.models.Player
import com.example.clashofbattle.utils.getPlayerJob
import com.example.clashofbattle.utils.loadImage

class ListPlayersAdapter(val clickListener:(Long)->Unit) : ListAdapter<Player,PlayerViewHolder>(PlayerDiffCallback()) {
    override fun onCreateViewHolder(parent:ViewGroup, viewType : Int): PlayerViewHolder {
        return PlayerViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val item=getItem(position)
        holder.bind(clickListener,item)
    }
}

class PlayerViewHolder private constructor(var binding : FragmentHorizontalListeJoueurBinding):RecyclerView.ViewHolder(binding.root) {
    fun bind(clickListener: (Long) -> Unit, item: Player) {
        binding.nomjoueur.text=item.name
        loadImage(binding.imagejoueur,item.imageUrl)
        binding.classejoueur.text= getPlayerJob(player = item).name
        binding.root.setOnClickListener{clickListener(item.id)}
    }

    companion object {
        fun from(parent: ViewGroup): PlayerViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = FragmentHorizontalListeJoueurBinding.inflate(layoutInflater, parent, false)
            return PlayerViewHolder(binding)
        }
    }
}