package com.example.mynotesapp.domain.note.usecase

import com.example.mynotesapp.domain.note.repository.NoteRepository
import javax.inject.Inject

class DeleteNote @Inject constructor(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id: List<Int>) {
        noteRepository.deleteNote(id)
    }
}