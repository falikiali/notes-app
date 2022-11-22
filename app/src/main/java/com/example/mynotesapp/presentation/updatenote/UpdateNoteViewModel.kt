package com.example.mynotesapp.presentation.updatenote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotesapp.domain.note.usecase.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

interface UpdateNoteViewModelContract {
    fun updateNote(id: Int, title: String?, content: String?, timestamp: Long)
    fun deleteNote(id: List<Int>)
}

@HiltViewModel
class UpdateNoteViewModel @Inject constructor(private val noteUseCase: NoteUseCase) : ViewModel(), UpdateNoteViewModelContract {
    override fun updateNote(id: Int, title: String?, content: String?, timestamp: Long) {
        viewModelScope.launch {
            noteUseCase.updateNote.invoke(id, title, content, timestamp)
        }
    }

    override fun deleteNote(id: List<Int>) {
        viewModelScope.launch {
            noteUseCase.deleteNote.invoke(id)
        }
    }
}