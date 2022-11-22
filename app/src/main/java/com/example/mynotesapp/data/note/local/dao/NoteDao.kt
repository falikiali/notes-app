package com.example.mynotesapp.data.note.local.dao

import androidx.room.*
import com.example.mynotesapp.data.note.local.dto.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM tbl_note ORDER BY updated_at DESC")
    fun getAllNotes() : Flow<List<Note>>

    @Query("DELETE FROM tbl_note WHERE id IN (:id)")
    suspend fun deleteNotes(id: List<Int>)

    @Query("UPDATE tbl_note SET title = :title, content = :content, updated_at = :timestamp WHERE id = :id")
    suspend fun updateNotes(id: Int, title: String?, content: String?, timestamp: Long)
}