package com.example.notesapp.repository

import com.example.notesapp.db.NotesDatabase
import com.example.notesapp.model.Notes

class NotesRepository(private val db: NotesDatabase) {

    suspend fun insertNotes(notes: Notes) = db.getNotesDao().insertNotes(notes)
    suspend fun updateNotes(notes: Notes) = db.getNotesDao().updateNotes(notes)
    suspend fun deleteNotes(notes: Notes) = db.getNotesDao().deleteNotes(notes)
    fun getAllNotes() = db.getNotesDao().getAllNotes()
    fun searchNotes(query: String?) = db.getNotesDao().searchNotes(query)
}