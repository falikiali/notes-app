package com.example.mynotesapp.domain.note.usecase

data class NoteUseCase(
    val insertNote: InsertNote,
    val retrieveAllNotes: RetrieveAllNotes,
    val deleteNote: DeleteNote,
    val updateNote: UpdateNote
)
