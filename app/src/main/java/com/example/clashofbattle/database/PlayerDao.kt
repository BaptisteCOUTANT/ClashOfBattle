package com.example.clashofbattle.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.clashofbattle.models.Player

@Dao
interface PlayerDao {
    @Insert
    suspend fun insertAll(players: List<Player>)

    @Query("DELETE FROM Player")
    suspend fun clear()

    @Transaction
    suspend fun replace(players: List<Player>) {
        clear()
        insertAll(players)
    }

    @Insert
    fun insert(player:Player)

    @Query("SELECT * FROM Player WHERE id= :id")
    operator fun get(id: Long) : Player

    @Query("SELECT * FROM Player")
    fun getAll():List<Player>

    @Query("SELECT * FROM Player ORDER BY name")
    fun getAlls():LiveData<List<Player?>>

    @Update
    fun update(player: Player)

    @Delete
    fun delete(player : Player)
}