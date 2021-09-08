package com.example.notesapp.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.MainActivity
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentUpdateNotesBinding
import com.example.notesapp.model.Notes
import com.example.notesapp.toast
import com.example.notesapp.viewmodel.NotesViewModel

class UpdateNotesFragment : Fragment(R.layout.fragment_update_notes) {

    private var _binding: FragmentUpdateNotesBinding? = null
    private val binding get() = _binding!!

    private val args: UpdateNotesFragmentArgs by navArgs()
    private lateinit var currentNotes: Notes
    private lateinit var notesViewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUpdateNotesBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel = (activity as MainActivity).notesViewModel
        currentNotes = args.notes!!

        binding.etNotesBodyUpdate.setText(currentNotes.noteTitle)
        binding.etNotesTitleUpdate.setText(currentNotes.noteBody)

        binding.fabDone.setOnClickListener {
            val title = binding.etNotesTitleUpdate.text.toString().trim()
            val body = binding.etNotesBodyUpdate.text.toString().trim()

            if (title.isNotEmpty()) {
                val notes = Notes(currentNotes.id, title, body)
                notesViewModel.updateNotes(notes)
                activity?.toast("Notes Updated!")
                view.findNavController().navigate(R.id.action_updateNotesFragment_to_homeFragment)

            } else {
                activity?.toast("Enter a note title please")
            }
        }
    }

    private fun deleteNotes() {
        AlertDialog.Builder(activity).apply {
            setTitle("Delete Note")
            setMessage("Are you sure you want to permanently delete this note?")
            setPositiveButton("DELETE") { _, _ ->
                notesViewModel.deleteNotes(currentNotes)
                view?.findNavController()?.navigate(
                    R.id.action_updateNotesFragment_to_homeFragment
                )
            }
            setNegativeButton("CANCEL", null)
        }.create().show()

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.update_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.delete_menu -> {
                deleteNotes()
            }
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}