package com.example.mynotesapp.presentation.addnote

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import com.example.mynotesapp.R
import com.example.mynotesapp.databinding.ActivityAddNoteBinding
import com.example.mynotesapp.domain.note.entity.NoteEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    private val noteViewModel: NoteViewModel by viewModels()
    private var menuSave: Menu? = null
    private var isTitleEmpty: Boolean? = true
    private var isContentEmpty: Boolean? = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        editTitleListener()
        editContentListener()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuSave = menu
        menuInflater.inflate(R.menu.menu_main, menuSave)
        showMenuSave(false)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_save -> {
                hideKeyboard()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (isContentEmpty == false || isTitleEmpty == false) {
            addNewNotes()
        }
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun getTitleNotes() : String {
        return binding.etTitle.text.toString()
    }

    private fun getContentNotes() : String {
        return binding.etContent.text.toString()
    }

    private fun editTitleListener() {
        binding.etTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                showMenuSave(true)
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0?.isEmpty() == true) {
                    isTitleEmpty = true
                    if (isContentEmpty == true) {
                        showMenuSave(false)
                    }
                } else {
                    isTitleEmpty = false
                }
            }
        })
    }

    private fun editContentListener() {
        binding.etContent.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                showMenuSave(true)
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0?.isEmpty() == true) {
                    isContentEmpty = true
                    if (isTitleEmpty == true) {
                        showMenuSave(false)
                    }
                } else {
                    isContentEmpty = false
                }
            }
        })
    }

    private fun showMenuSave(isShow: Boolean) {
        menuSave?.findItem(R.id.menu_save)?.isVisible = isShow
    }

    private fun addNewNotes() {
        val timestamp = System.currentTimeMillis()

        noteViewModel.insertNote(
            NoteEntity(
                title = getTitleNotes(),
                content = getContentNotes(),
                timestamp = timestamp
            )
        )
    }

    private fun hideKeyboard() {
        val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var currentFocus = this.currentFocus
        if (currentFocus == null) {
            currentFocus = View(this)
        }
        imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
    }
}