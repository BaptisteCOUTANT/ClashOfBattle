package com.example.clashofbattle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.clashofbattle.database.AppDatabase
import com.example.clashofbattle.database.ListPlayersAdapter
import com.example.clashofbattle.databinding.FragmentListeJoueurBinding
import com.example.clashofbattle.models.Player
import com.example.clashofbattle.utils.getPlayerJob
import com.example.clashofbattle.utils.loadImage
import com.example.clashofbattle.viewmodel.ClashOfBattleViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListeJoueur : Fragment() {

    private var _binding: FragmentListeJoueurBinding? = null
    private lateinit var viewModel : ClashOfBattleViewModel
    private val binding get() = _binding!!
    private var player : Player? =null
    private val joueurID ="Louis"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel= ViewModelProvider(this).get(ClashOfBattleViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListeJoueurBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter= ListPlayersAdapter{

            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        var Data = joueurID
        var arguments:Bundle  = bundleOf("myPlayer" to Data)
       binding.card.setOnClickListener {view->
            findNavController().navigate(R.id.action_FirstFragment_to_editPlayerFragment,arguments)
       }

        player=AppDatabase.INSTANCE?.playerDao()?.getByRemoteId("Louis")
        player?.imageUrl?.let { loadImage(binding.imagejoueurconnecte, it) }
        binding.nomjoueurconnecte.text=player?.name
        binding.classejoueurconnecte.text= player?.let { getPlayerJob(it).name }
        binding.rvJoueurs.adapter=adapter
        viewModel.daoPlayers?.observe(viewLifecycleOwner){adapter.submitList(it.toList())}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}