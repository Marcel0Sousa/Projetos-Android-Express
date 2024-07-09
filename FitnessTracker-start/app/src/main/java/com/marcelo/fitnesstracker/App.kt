package com.marcelo.fitnesstracker

import android.app.Application
import com.marcelo.fitnesstracker.model.AppDatabase

class App: Application() {
    lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()
        db = AppDatabase.getDatabase(this)
    }
}