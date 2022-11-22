package com.example.mynotesapp.data.note

import com.example.mynotesapp.data.AppDatabase
import com.example.mynotesapp.data.DatabaseModule
import com.example.mynotesapp.data.note.local.dao.NoteDao
import com.example.mynotesapp.data.note.repository.ImplNoteRepository
import com.example.mynotesapp.domain.note.repository.NoteRepository
import com.example.mynotesapp.domain.note.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module(includes = [DatabaseModule::class])
@InstallIn(SingletonComponent::class)
class NoteModule {
    @Singleton
    @Provides
    fun provideNoteDao(appDatabase: AppDatabase) : NoteDao = appDatabase.noteDao()

    @Singleton
    @Provides
    fun provideNoteRepository(noteDao: NoteDao) : NoteRepository =
        ImplNoteRepository(noteDao)

    @Singleton
    @Provides
    fun provideNoteUseCase(noteRepository: NoteRepository) : NoteUseCase =
        NoteUseCase(
            InsertNote(noteRepository),
            RetrieveAllNotes(noteRepository),
            DeleteNote(noteRepository),
            UpdateNote(noteRepository)
        )
}