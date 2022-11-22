package com.example.mynotesapp.utils

import com.example.mynotesapp.data.note.local.dto.Note
import com.example.mynotesapp.domain.note.entity.NoteEntity

object DataMapper {
    fun mapNoteDataToDomain(data: List<Note>) : List<NoteEntity> =
        data.map {
            NoteEntity(
                id = it.id,
                title = it.title!!,
                content = it.content!!,
                timestamp = it.updatedAt!!
            )
        }
}