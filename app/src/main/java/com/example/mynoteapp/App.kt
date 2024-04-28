package com.example.mynoteapp

import android.app.Application
import androidx.room.Room
import com.example.mynoteapp.data.db.AppDatabase
import com.example.mynoteapp.utils.Pref

class App: Application() {

    companion object{
        var appDatabase: AppDatabase? = null
    }
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = Pref
        sharedPreferences.unit(this)
        getInstance()
    }
    fun getInstance():AppDatabase? {
        if (appDatabase == null){
            appDatabase = applicationContext?.let {
                Room.databaseBuilder(
                    it,
                    AppDatabase::class.java,
                    "note.database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return appDatabase
    }
}