package com.example.clashofbattle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.clashofbattle.database.AppDatabase
import com.example.clashofbattle.databinding.FragmentEditPlayerBinding
import com.example.clashofbattle.databinding.FragmentListeJoueurBinding
import com.example.clashofbattle.models.Player
import com.example.clashofbattle.utils.loadImage


class EditPlayerFragment : Fragment() {

    private var _binding: FragmentEditPlayerBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditPlayerBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var remoteId : String? =arguments?.getString("myPlayer")
        var player = remoteId?.let { AppDatabase.INSTANCE?.playerDao()?.getByRemoteId(it) }

        binding.playerName.setText(player?.name)
        player?.imageUrl?.let { loadImage(binding.cardImage, it) }
        binding.playerUrlProfil.setText(player?.imageUrl)
        binding.competence1.text=player?.capability1?.name
        binding.competence2.text=player?.capability2?.name
        binding.competence3.text=player?.capability3?.name
    }

}