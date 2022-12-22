package com.example.clashofbattle.database

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.clashofbattle.databinding.FragmentHorizontalListeJoueurBinding
import com.example.clashofbattle.models.Capability
import com.example.clashofbattle.models.CapabilityType
import com.example.clashofbattle.models.Player
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
        binding.classejoueur.text=getType(item.capabilities)
        binding.root.setOnClickListener{clickListener(item.id)}
    }
    private fun getType(listCapability: List<Capability>):String{
        var attack :Int = 0
        var defense :Int =0
        var heal :Int =0
        var type:String ="Work In Progress"
        var capabilityType1 = listCapability[0].type
        var capabilityType2=listCapability[1].type
        var capabilityType3=listCapability[2].type
        when(capabilityType1){
            CapabilityType.ATTACK-> attack++
            CapabilityType.DEFENSE ->defense++
            CapabilityType.HEAL->heal++
        }
        when(capabilityType2){
            CapabilityType.ATTACK-> attack++
            CapabilityType.DEFENSE ->defense++
            CapabilityType.HEAL->heal++
        }
        when(capabilityType3){
            CapabilityType.ATTACK-> attack++
            CapabilityType.DEFENSE ->defense++
            CapabilityType.HEAL->heal++
        }
        if(attack>1)type="Guerrier"
        if(defense>1)type="Chevalier"
        if(heal>1)type="Prêtre"
        if(attack==defense&&defense==heal)type="Barde"
        return type
    }

    companion object {
        fun from(parent: ViewGroup): PlayerViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = FragmentHorizontalListeJoueurBinding.inflate(layoutInflater, parent, false)
            return PlayerViewHolder(binding)
        }
    }
}