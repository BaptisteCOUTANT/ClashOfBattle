package com.example.clashofbattle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModelProvider
import com.example.clashofbattle.capabilities.SelectCapabilityActivity
import com.example.clashofbattle.database.AppDatabase
import com.example.clashofbattle.databinding.FragmentEditPlayerBinding
import com.example.clashofbattle.utils.getColor
import com.example.clashofbattle.utils.getNameId
import com.example.clashofbattle.utils.loadImage
import com.example.clashofbattle.viewmodel.ClashOfBattleViewModel
import com.example.clashofbattle.viewmodel.EditPlayerViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class EditPlayerFragment : Fragment() {

    private var _binding: FragmentEditPlayerBinding? = null
    private lateinit var viewModel:EditPlayerViewModel
    private val selectCapabilityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        it.data?.let { intent -> val pair = SelectCapabilityActivity.extractResultData(intent)
            viewModel.updateCapability(pair.first,pair.second)
        }
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel= ViewModelProvider(this).get(EditPlayerViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditPlayerBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var remoteId : String ="Louis"
        var player = AppDatabase?.INSTANCE?.playerDao()?.getByRemoteId(remoteId)
        checkNotNull(player)
        binding.playerName.setText(player.name)
        player.imageUrl.let { loadImage(binding.cardImage, it) }
        binding.playerUrlProfil.setText(player.imageUrl)
        binding.competence1.setTextColor(player.capability1.getColor(requireContext()))
        binding.competence2.setTextColor(player.capability2.getColor(requireContext()))
        binding.competence3.setTextColor(player.capability3.getColor(requireContext()))
        binding.competence1.setText(player.capability1.getNameId())
        binding.competence2.setText(player.capability2.getNameId())
        binding.competence3.setText(player.capability3.getNameId())

        binding.buttonCompetence1.setOnClickListener {
            selectCapabilityLauncher.launch(SelectCapabilityActivity.newIntent(requireContext(),0))
        }
        binding.buttonCompetence2.setOnClickListener {
            selectCapabilityLauncher.launch(SelectCapabilityActivity.newIntent(requireContext(),1))
        }
        binding.buttonCompetence3.setOnClickListener {
            selectCapabilityLauncher.launch(SelectCapabilityActivity.newIntent(requireContext(),2))
        }

        binding.buttonValider.setOnClickListener {
           runBlocking {
               viewModel.validate(
                   binding.playerName.text.toString(),
                   binding.playerUrlProfil.text.toString()
               )
           }
        }
    }

}