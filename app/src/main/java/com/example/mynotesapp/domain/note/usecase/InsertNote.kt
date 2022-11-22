package com.example.mynotesapp.domain.note.usecase

import com.example.mynotesapp.domain.note.entity.NoteEntity
import com.example.mynotesapp.domain.note.repository.NoteRepository
import javax.inject.Inject

class InsertNote @Inject constructor(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: NoteEntity) {
        noteRepository.insertNote(note)
    }
}