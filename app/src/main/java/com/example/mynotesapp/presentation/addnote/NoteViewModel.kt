package com.example.mynotesapp.presentation.addnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotesapp.domain.note.entity.NoteEntity
import com.example.mynotesapp.domain.note.usecase.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

interface NoteViewModelContract {
    fun insertNote(note: NoteEntity)
}

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteUseCase: NoteUseCase) : ViewModel(), NoteViewModelContract {
    override fun insertNote(note: NoteEntity) {
        viewModelScope.launch {
            noteUseCase.insertNote.invoke(note)
        }
    }
}