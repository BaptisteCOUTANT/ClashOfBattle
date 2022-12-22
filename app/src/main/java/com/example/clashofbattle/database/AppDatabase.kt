package com.example.clashofbattle.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.clashofbattle.database.PlayerDao
import com.example.clashofbattle.models.Player

@Database(entities= arrayOf(Player::class), version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun playerDao(): PlayerDao

    companion object {
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context):AppDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance= Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"ClashOfBattle")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .addCallback(object : RoomDatabase.Callback(){
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                var dao = INSTANCE?.playerDao();
                                TODO()
                            }
                        }).build()
                    INSTANCE=instance
                }
                return instance
            }
        }
    }
}