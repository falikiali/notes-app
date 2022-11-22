package com.example.mynotesapp.presentation.updatenote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.mynotesapp.databinding.ActivityUpdateNoteBinding
import com.example.mynotesapp.domain.note.entity.NoteEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateNoteActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityUpdateNoteBinding

    private var listId = ArrayList<Int>()
    private val updateNoteViewModel: UpdateNoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setActionBar()
        showOldNote()
    }

    private fun getTitleNotes() : String {
        return binding.etTitle.text.toString()
    }

    private fun getContentNotes() : String {
        return binding.etContent.text.toString()
    }

    private fun getIntentData() : NoteEntity {
        return intent.getParcelableExtra(EXTRA_DATA)!!
    }

    override fun onBackPressed() {
        val note = getIntentData().id
        listId.add(note!!)
        val timestamp = System.currentTimeMillis()
        val title = getTitleNotes()
        val content = getContentNotes()

        if (title.isEmpty() && content.isEmpty()) {
            updateNoteViewModel.deleteNote(listId)
        } else if (title != getIntentData().title || content != getIntentData().content) {
            updateNoteViewModel.updateNote(note, title, content, timestamp)
        }
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showOldNote() {
        val note = getIntentData()

        binding.etTitle.setText(note.title)
        binding.etContent.setText(note.content)
    }
}