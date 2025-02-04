// File: presentation/add_edit_note/AddEditNoteViewModel.kt

package com.myprojects.audionotes.presentation.add_edit_note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.model.NoteModel
import com.example.notesapp.data.model.NoteType
import com.myprojects.audionotes.domain.UseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date

/**
 * ViewModel для экрана добавления/редактирования заметки.
 * Содержит логику сохранения, загрузки и редактирования выбранной заметки.
 */
class AddEditNoteViewModel(
    private val useCases: UseCases
) : ViewModel() {

    private val _currentNote = MutableStateFlow<NoteModel?>(null)
    val currentNote = _currentNote.asStateFlow()

    fun loadNote(noteId: String) {
        viewModelScope.launch {
            useCases.getNoteById(noteId).collect { note ->
                _currentNote.value = note
            }
        }
    }

    fun saveNote(
        title: String,
        content: String,
        noteType: NoteType,
        category: String? = null
    ) {
        viewModelScope.launch {
            val existingNote = _currentNote.value
            val updatedNote = existingNote?.copy(
                title = title,
                content = content,
                noteType = noteType,
                category = category
            ) ?: NoteModel(
                id = "",
                title = title,
                content = content,
                noteType = noteType,
                createdAt = Date(),
                category = category
            )

            if (existingNote == null) {
                useCases.addNote(updatedNote)
            } else {
                useCases.updateNote(updatedNote)
            }
        }
    }
}
