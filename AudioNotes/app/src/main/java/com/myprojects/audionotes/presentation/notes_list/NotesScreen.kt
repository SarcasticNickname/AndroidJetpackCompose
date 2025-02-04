// File: presentation/notes_list/NotesScreen.kt

package com.myprojects.audionotes.presentation.notes_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.myprojects.audionotes.presentation.components.NoteItem

/**
 * Composable-функция экрана со списком заметок.
 * Принимает ViewModel, а также коллбеки для перехода на другие экраны.
 */
@Composable
fun NotesScreen(
    viewModel: NotesViewModel,
    onNoteClick: (String) -> Unit,
    onAddNoteClick: () -> Unit
) {
    val notesList by viewModel.notes.collectAsState()

    // Кнопка для добавления новой заметки
    Button(onClick = onAddNoteClick) {
        Text("Добавить заметку")
    }

    // Отображаем список заметок
    LazyColumn {
        items(notesList) { note ->
            NoteItem(
                note = note,
                onClick = { onNoteClick(note.id) },
                onDeleteClick = { viewModel.deleteNote(note) }
            )
        }
    }
}
