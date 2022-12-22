package com.example.clashofbattle.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.clashofbattle.models.Player

@Dao
interface PlayerDao {
    @Insert
    fun insert(player:Player)

    @Query("SELECT * FROM Player WHERE id= :id")
    operator fun get(id: Long) : Player

    @Query("SELECT * FROM Player")
    fun getAll():List<Player>

    @Query("SELECT * FROM Player ORDER BY name")
    fun get():LiveData<List<Player?>>

    @Update
    fun update(player: Player)

    @Delete
    fun delete(player : Player)
}