package com.example.clashofbattle.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clashofbattle.api.ClashOfBattleAPI
import com.example.clashofbattle.database.AppDatabase
import com.example.clashofbattle.database.PlayerDao
import com.example.clashofbattle.models.Capability
import com.example.clashofbattle.models.Player
import kotlinx.coroutines.launch

class EditPlayerViewModel :ViewModel(){
    // Dépendances
    private val playerDao: PlayerDao by lazy { AppDatabase.INSTANCE!!.playerDao() }

    private val playerApi: ClashOfBattleAPI by lazy { ClashOfBattleAPI.service }


    val myPlayer = MutableLiveData<Player>()

    init {
        viewModelScope.launch {
            myPlayer.value = playerDao.getByRemoteId("Louis")
        }
    }

    fun updateCapability(index: Int, capability: Capability?) {
        capability?.let {
            val player = checkNotNull(myPlayer.value)

            myPlayer.value = when(index) {
                0 -> player.copy(capability1 = capability)
                1 -> player.copy(capability2 = capability)
                2 -> player.copy(capability3 = capability)
                else -> throw IllegalStateException("No capability for index $index")
            }
        }
    }

    suspend fun validate(name: String, imageUrl: String) {
        myPlayer.value?.let { player ->

            val modifiedPlayer = player.copy(
                name = name,
                imageUrl = imageUrl
            )

            val remoteId = checkNotNull(modifiedPlayer.remoteId)
            playerApi.editmyPlayer(remoteId,modifiedPlayer)
            playerDao.update(modifiedPlayer)
        }
    }
}