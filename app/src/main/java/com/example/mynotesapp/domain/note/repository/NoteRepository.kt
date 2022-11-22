package com.example.mynotesapp.domain.note.repository

import com.example.mynotesapp.domain.note.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun insertNote(note: NoteEntity)
    fun getAllNotes(): Flow<List<NoteEntity>>
    suspend fun deleteNote(id: List<Int>)
    suspend fun updateNote(id: Int, title: String?, content: String?, timestamp: Long)
}