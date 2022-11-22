package com.example.mynotesapp.domain.note.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteEntity(
    val id: Int? = 0,
    val title: String,
    val content: String,
    val timestamp: Long
) : Parcelable