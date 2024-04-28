package com.example.mynoteapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mynoteapp.data.db.daos.NoteDao
import com.example.mynoteapp.data.model.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}