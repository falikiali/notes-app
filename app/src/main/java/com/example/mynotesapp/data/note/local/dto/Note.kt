package com.example.mynotesapp.data.note.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "content")
    var content: String? = null,

    @ColumnInfo(name = "created_at")
    var createdAt: Long? = null,

    @ColumnInfo(name = "updated_at")
    var updatedAt: Long? = null
)