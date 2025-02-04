// File: data/repository/NotesRepositoryImpl.kt

package com.myprojects.audionotes.data.repository

import com.myprojects.audionotes.data.local.dao.NoteDao
import com.myprojects.audionotes.data.local.entity.NoteEntity
import com.example.notesapp.data.model.NoteModel
import com.example.notesapp.data.model.NoteType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date

/**
 * Реализация репозитория, использующая NoteDao для взаимодействия с Room.
 */
class NotesRepositoryImpl(
    private val noteDao: NoteDao
) : NotesRepository {

    override fun getAllNotes(): Flow<List<NoteModel>> {
        return noteDao.getAllNotes().map { entityList ->
            entityList.map { it.toNoteModel() }
        }
    }

    override fun getNoteById(id: String): Flow<NoteModel?> {
        return noteDao.getNoteById(id).map { entity ->
            entity?.toNoteModel()
        }
    }

    override suspend fun addNote(note: NoteModel) {
        noteDao.insertNote(note.toNoteEntity())
    }

    override suspend fun updateNote(note: NoteModel) {
        noteDao.updateNote(note.toNoteEntity())
    }

    override suspend fun deleteNote(note: NoteModel) {
        noteDao.deleteNote(note.toNoteEntity())
    }

    /**
     * Преобразование NoteEntity в NoteModel.
     */
    private fun NoteEntity.toNoteModel() = NoteModel(
        id = id,
        title = title,
        content = content,
        noteType = when(noteType) {
            "AUDIO" -> NoteType.AUDIO
            else -> NoteType.TEXT
        },
        createdAt = Date(createdAt),
        category = category,
        audioFilePath = audioFilePath
    )

    /**
     * Преобразование NoteModel в NoteEntity.
     */
    private fun NoteModel.toNoteEntity() = NoteEntity(
        id = id,
        title = title,
        content = content,
        noteType = noteType.name,
        createdAt = createdAt.time,
        category = category,
        audioFilePath = audioFilePath
    )
}
