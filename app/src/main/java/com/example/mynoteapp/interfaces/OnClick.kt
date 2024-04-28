package com.example.mynoteapp.interfaces

import com.example.mynoteapp.data.model.NoteModel

interface OnClick {
    fun onLongClick(noteModel: NoteModel)
}