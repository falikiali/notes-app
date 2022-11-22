package com.example.mynotesapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mynotesapp.data.note.local.dao.NoteDao
import com.example.mynotesapp.data.note.local.dto.Note

@Database(entities = [Note::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}