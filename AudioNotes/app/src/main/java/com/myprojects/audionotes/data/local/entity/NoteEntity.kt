// File: data/local/entity/NoteEntity.kt

package com.myprojects.audionotes.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

/**
 * Сущность для хранения заметки в базе данных.
 */
@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val title: String,
    val content: String,
    val noteType: String, // Используем String для enum-а (TEXT/AUDIO)
    val createdAt: Long = Date().time, // Храним время в миллисекундах
    val category: String? = null,
    val audioFilePath: String? = null
)
