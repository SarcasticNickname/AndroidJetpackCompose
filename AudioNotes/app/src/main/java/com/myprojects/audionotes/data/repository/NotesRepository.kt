// File: data/repository/NotesRepository.kt

package com.myprojects.audionotes.data.repository

import com.example.notesapp.data.model.NoteModel
import kotlinx.coroutines.flow.Flow

/**
 * Интерфейс репозитория, содержащий операции над заметками.
 */
interface NotesRepository {
    fun getAllNotes(): Flow<List<NoteModel>>
    fun getNoteById(id: String): Flow<NoteModel?>
    suspend fun addNote(note: NoteModel)
    suspend fun updateNote(note: NoteModel)
    suspend fun deleteNote(note: NoteModel)
}