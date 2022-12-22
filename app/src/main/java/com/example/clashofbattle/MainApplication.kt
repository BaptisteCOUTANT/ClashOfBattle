package com.example.clashofbattle

import android.app.Application
import android.util.Log
import com.example.clashofbattle.database.AppDatabase

class MainApplication:Application() {
        override fun onCreate() {
        super.onCreate()
        AppDatabase.init(this)
    }
}