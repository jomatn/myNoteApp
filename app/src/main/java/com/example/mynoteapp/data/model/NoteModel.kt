package com.example.mynoteapp.data.model

import android.graphics.Color
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "noteModel")
@Parcelize
data class NoteModel(
 @PrimaryKey(autoGenerate = true)
 var id: Int? = null,
 val title: String,
 val text: String,
 val time: String,
 val date: String,
 val color: Int = Color.BLACK
) : Parcelable
