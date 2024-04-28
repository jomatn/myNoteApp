package com.example.mynoteapp.interfaces

import com.example.mynoteapp.data.model.NoteModel

interface OnClickItem {
    fun onLongClick(noteModel: NoteModel)
}