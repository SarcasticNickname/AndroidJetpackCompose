// File: presentation/notes_list/NotesViewModel.kt

package com.myprojects.audionotes.presentation.notes_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.model.NoteModel
import com.myprojects.audionotes.domain.UseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel для экрана со списком заметок.
 * Здесь загружаются данные и обрабатываются действия пользователя,
 * такие как удаление заметки.
 */
class NotesViewModel(
    private val useCases: UseCases
) : ViewModel() {

    private val _notes = MutableStateFlow<List<NoteModel>>(emptyList())
    val notes = _notes.asStateFlow()

    init {
        loadNotes()
    }

    private fun loadNotes() {
        viewModelScope.launch {
            useCases.getAllNotes().collect { noteList ->
                _notes.value = noteList
            }
        }
    }

    fun deleteNote(note: NoteModel) {
        viewModelScope.launch {
            useCases.deleteNote(note)
        }
    }
}
