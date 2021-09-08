package com.example.notesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.model.Notes
import com.example.notesapp.repository.NotesRepository
import kotlinx.coroutines.launch

class NotesViewModel(
    app: Application,
    private val notesRepository: NotesRepository
) : AndroidViewModel(app) {

    fun addNotes(notes: Notes) = viewModelScope.launch {
        notesRepository.insertNotes(notes)
    }

    fun updateNotes(notes: Notes) = viewModelScope.launch {
        notesRepository.updateNotes(notes)
    }

    fun deleteNotes(notes: Notes) = viewModelScope.launch {
        notesRepository.deleteNotes(notes)
    }

    fun getAllNotes() = notesRepository.getAllNotes()

    fun searchNotes(query: String?) = notesRepository.searchNotes(query)
}