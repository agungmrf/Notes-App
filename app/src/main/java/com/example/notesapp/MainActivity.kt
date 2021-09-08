package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.db.NotesDatabase
import com.example.notesapp.repository.NotesRepository
import com.example.notesapp.viewmodel.NotesViewModel
import com.example.notesapp.viewmodel.NotesViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var notesViewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        setUpViewModel()
    }

    private fun setUpViewModel() {
        val notesRepository = NotesRepository(
            NotesDatabase(this)
        )

        val viewModelProviderFactory =
            NotesViewModelProviderFactory(
                application, notesRepository
            )

        notesViewModel = ViewModelProvider(
            this,
            viewModelProviderFactory
        ).get(NotesViewModel::class.java)
    }

}