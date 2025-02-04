// File: domain/UseCases.kt

package com.myprojects.audionotes.domain

import com.example.notesapp.data.model.NoteModel
import com.myprojects.audionotes.data.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class UseCases(
    private val repository: NotesRepository
) {
    fun getAllNotes(): Flow<List<NoteModel>> = repository.getAllNotes()
    fun getNoteById(id: String): Flow<NoteModel?> = repository.getNoteById(id)

    suspend fun addNote(note: NoteModel) = repository.addNote(note)
    suspend fun updateNote(note: NoteModel) = repository.updateNote(note)
    suspend fun deleteNote(note: NoteModel) = repository.deleteNote(note)
}
