package com.example.mynotesapp.domain.note.usecase

import com.example.mynotesapp.domain.note.repository.NoteRepository
import javax.inject.Inject

class UpdateNote @Inject constructor(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id: Int, title: String?, content: String?, timestamp: Long) {
        noteRepository.updateNote(id, title, content, timestamp)
    }
}