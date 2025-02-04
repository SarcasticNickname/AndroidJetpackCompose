// File: presentation/add_edit_note/AddEditNoteScreen.kt

package com.myprojects.audionotes.presentation.add_edit_note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.notesapp.data.model.NoteType

/**
 * Composable-функция экрана добавления/редактирования заметки.
 * Показывает поля ввода и кнопку "Сохранить".
 */
@Composable
fun AddEditNoteScreen(
    viewModel: AddEditNoteViewModel,
    noteId: String?,
    onSaveSuccess: () -> Unit
) {
    // Загрузка заметки, если noteId не пустое
    LaunchedEffect(noteId) {
        if (!noteId.isNullOrEmpty()) {
            viewModel.loadNote(noteId)
        }
    }

    val noteState by viewModel.currentNote.collectAsState()

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var isAudio by remember { mutableStateOf(false) }

    // При изменении загруженной заметки обновляем поля
    LaunchedEffect(noteState) {
        noteState?.let { note ->
            title = note.title
            content = note.content
            isAudio = (note.noteType == NoteType.AUDIO)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        BasicTextField(
            value = title,
            onValueChange = { title = it }
        )
        BasicTextField(
            value = content,
            onValueChange = { content = it }
        )

        // Для упрощения используем флаг isAudio вместо полноценного переключателя типов
        Button(onClick = { isAudio = !isAudio }) {
            Text(text = if (isAudio) "Тип: Аудио" else "Тип: Текст")
        }

        Button(onClick = {
            viewModel.saveNote(
                title = title,
                content = content,
                noteType = if (isAudio) NoteType.AUDIO else NoteType.TEXT
            )
            onSaveSuccess()
        }) {
            Text("Сохранить")
        }
    }
}
