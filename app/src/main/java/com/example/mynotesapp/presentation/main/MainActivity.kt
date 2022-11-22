package com.example.mynotesapp.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynotesapp.R
import com.example.mynotesapp.databinding.ActivityMainBinding
import com.example.mynotesapp.presentation.addnote.AddNoteActivity
import com.example.mynotesapp.presentation.updatenote.UpdateNoteActivity
import com.example.mynotesapp.presentation.updatenote.UpdateNoteActivity.Companion.EXTRA_DATA
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var id = ArrayList<Int>()

    private var menuDelete: Menu? = null
    private val mainAdapter = MainAdapter()
    private val mainViewModel: MainViewModel by viewModels()
    private var isEnableSelect = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTittleToolbar()
        initRecyclerView()
        showData()
        btnAction()
        actionLongClickOnEachItem()
        actionClickOnEachItem()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuDelete = menu
        menuInflater.inflate(R.menu.menu_delete, menuDelete)
        showMenuDelete(false)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> {
                mainViewModel.deleteNote(id)
                showMenuDelete(false)
                id.clear()
                isEnableSelect = false
            }
            R.id.menu_cancel -> {
                id.clear()
                showMenuDelete(false)
                isEnableSelect = false
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        showData()
        isEnableSelect = false
    }

    override fun onResume() {
        super.onResume()
        showData()
        isEnableSelect = false
    }

    private fun setTittleToolbar() {
        binding.toolbar.apply {
            title = "Notes"
        }
        setSupportActionBar(binding.toolbar)
    }

    private fun initRecyclerView() {
        binding.content.rvNotes.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = mainAdapter
            setHasFixedSize(true)
        }
    }

    private fun showData() {
        mainViewModel.getAllNotes().observe(this@MainActivity) {
            mainAdapter.setData(it)
        }
    }

    private fun btnAction() {
        binding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun actionLongClickOnEachItem() {
        if (!isEnableSelect) {
            mainAdapter.onLongItemClick = {
                id.clear()
                id.add(it.id!!)
                isEnableSelect = true
                showMenuDelete(true)
                Toast.makeText(this@MainActivity, id.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun actionClickOnEachItem() {
        mainAdapter.onItemClick = { note, _ ->
            if (id.contains(note.id)) {
                id.remove(note.id)
                if (id.isEmpty()) {
                    showMenuDelete(false)
                    isEnableSelect = false
                }
            } else if (isEnableSelect) {
                id.add(note.id!!)
            } else if (!isEnableSelect) {
                val intent = Intent(this@MainActivity, UpdateNoteActivity::class.java)
                intent.putExtra(EXTRA_DATA, note)
                startActivity(intent)
            }

            if (id.isEmpty()) {
                supportActionBar?.title = "Notes"
            } else {
                supportActionBar?.title = "${id.size} Selected"
            }
        }
    }

    private fun showMenuDelete(isShow: Boolean) {
        menuDelete?.findItem(R.id.menu_delete)?.isVisible = isShow
        menuDelete?.findItem(R.id.menu_cancel)?.isVisible = isShow

        if (isShow) {
            supportActionBar?.title = "${id.size} Selected"
        } else {
            supportActionBar?.title = "Notes"
        }
    }
}