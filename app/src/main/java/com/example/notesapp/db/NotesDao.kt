package com.example.notesapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesapp.model.Notes

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: Notes)

    @Update
    suspend fun updateNotes(notes: Notes)

    @Delete
    suspend fun deleteNotes(notes: Notes)

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM notes WHERE noteTitle LIKE :query OR noteBody LIKE:query")
    fun searchNotes(query: String?): LiveData<List<Notes>>
}