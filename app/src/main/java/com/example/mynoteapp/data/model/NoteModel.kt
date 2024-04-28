package com.example.mynoteapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteModel")
data class NoteModel(
 val title: String,
){
 @PrimaryKey(autoGenerate = true)
 var id : Int = 0
}
