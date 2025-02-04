// File: data/model/NoteModel.kt

package com.example.notesapp.data.model

import java.util.Date

/**
 * Модель заметки в коде приложения.
 * Сопоставляется с NoteEntity в базе данных.
 */
data class NoteModel(
    val id: String,
    val title: String,
    val content: String,
    val noteType: NoteType,
    val createdAt: Date,
    val category: String?,
    val audioFilePath: String? = null
)

/**
 * Перечисление для типа заметки (текстовая или аудио).
 */
enum class NoteType {
    TEXT,
    AUDIO
}
