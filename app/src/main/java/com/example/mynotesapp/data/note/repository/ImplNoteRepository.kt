package com.example.mynotesapp.data.note.repository

import com.example.mynotesapp.data.note.local.dao.NoteDao
import com.example.mynotesapp.data.note.local.dto.Note
import com.example.mynotesapp.domain.note.entity.NoteEntity
import com.example.mynotesapp.domain.note.repository.NoteRepository
import com.example.mynotesapp.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImplNoteRepository @Inject constructor(private val noteDao: NoteDao) : NoteRepository {
    override suspend fun insertNote(note: NoteEntity) {
        return noteDao.insertNote(
            Note(
                title = note.title,
                content = note.content,
                createdAt = note.timestamp,
                updatedAt = note.timestamp
            )
        )
    }

    override fun getAllNotes(): Flow<List<NoteEntity>> {
        return noteDao.getAllNotes().map {
            DataMapper.mapNoteDataToDomain(it)
        }
    }

    override suspend fun deleteNote(id: List<Int>) {
        return noteDao.deleteNotes(id)
    }

    override suspend fun updateNote(id: Int, title: String?, content: String?, timestamp: Long) {
        return noteDao.updateNotes(id, title, content, timestamp)
    }
}