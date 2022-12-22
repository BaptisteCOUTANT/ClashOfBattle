package com.example.clashofbattle.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.clashofbattle.database.PlayerDao
import com.example.clashofbattle.models.Player
import com.example.clashofbattle.utils.CapabilityRoomConverter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities= [Player::class], version = 1)
@TypeConverters(CapabilityRoomConverter::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun playerDao(): PlayerDao

    companion object {
        @Volatile
        var INSTANCE : AppDatabase? = null

        fun init(context: Context) {
            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "ClashOfBattleDB"
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            val dao = INSTANCE?.playerDao()
                            GlobalScope.launch {
                                Log.d("DATABASE","C'est OK pour la DataBase")

                            }
                        }
                    })
                    .build()
            }
        }
    }
}