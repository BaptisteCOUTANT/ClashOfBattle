package com.example.clashofbattle.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clashofbattle.api.ClashOfBattleAPI
import com.example.clashofbattle.database.AppDatabase
import com.example.clashofbattle.database.PlayerDao
import com.example.clashofbattle.models.Player
import kotlinx.coroutines.launch

class ClashOfBattleViewModel: ViewModel() {
    val api: ClashOfBattleAPI = ClashOfBattleAPI.service
    val dao : PlayerDao? = AppDatabase.INSTANCE?.playerDao()
    var daoPlayers:LiveData<List<Player?>>?=null

    val players:LiveData<List<Player>>?=null

    init {
        getAll()
        getAllPlayersbyAPI()
    }

    fun getAll(){
        daoPlayers=dao?.getAlls()
    }

    fun getAllPlayersbyAPI(){
        viewModelScope.launch {
            var result=api.getAlls().toListOfPlayers()
            dao?.replace(result)
        }
    }
    private fun Map<String, Player>.toListOfPlayers() = entries.map {
        it.value.copy(remoteId = it.key)
    }
}