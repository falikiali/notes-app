package com.example.mynotesapp.domain.note.usecase

import com.example.mynotesapp.domain.note.entity.NoteEntity
import com.example.mynotesapp.domain.note.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RetrieveAllNotes @Inject constructor(private val noteRepository: NoteRepository) {
    fun invoke(): Flow<List<NoteEntity>> {
        return noteRepository.getAllNotes()
    }
}