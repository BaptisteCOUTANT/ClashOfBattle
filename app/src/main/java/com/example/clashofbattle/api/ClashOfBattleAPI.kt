package com.example.clashofbattle.api

import com.example.clashofbattle.models.Player
import com.example.clashofbattle.utils.CapabilityMoshiConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ClashOfBattleAPI {

    companion object {
        private const val BASE_URL = "https://firechat-dev-da136-default-rtdb.europe-west1.firebasedatabase.app"

        private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

        private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

        val service: ClashOfBattleAPI by lazy { retrofit.create(ClashOfBattleAPI::class.java) }
    }
    @GET("players.json")
    suspend fun getAlls():Map<String, Player>

    @PUT("players/{ID_DU_JOUEUR}.json")
    suspend fun editmyPlayer(@Path("ID_DU_JOUEUR") ID_DU_VOYAGE:String,@Body Player:Player):Player

}