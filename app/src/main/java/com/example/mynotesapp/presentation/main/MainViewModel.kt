package com.example.mynotesapp.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mynotesapp.domain.note.entity.NoteEntity
import com.example.mynotesapp.domain.note.usecase.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

interface MainViewModelContract {
    fun getAllNotes(): LiveData<List<NoteEntity>>
    fun deleteNote(id: List<Int>)
}

@HiltViewModel
class MainViewModel @Inject constructor(private val noteUseCase: NoteUseCase) : ViewModel(), MainViewModelContract {
    override fun getAllNotes(): LiveData<List<NoteEntity>> = noteUseCase.retrieveAllNotes.invoke().asLiveData()
    override fun deleteNote(id: List<Int>) {
        viewModelScope.launch {
            noteUseCase.deleteNote.invoke(id)
        }
    }
}