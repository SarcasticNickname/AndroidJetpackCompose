// File: presentation/components/NoteItem.kt

package com.myprojects.audionotes.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notesapp.data.model.NoteModel

/**
 * Элемент списка заметок.
 * Содержит краткую информацию и кнопки для взаимодействия.
 */
@Composable
fun NoteItem(
    note: NoteModel,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Column(Modifier.clickable { onClick() }) {
        Text(text = note.title)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = note.content.take(60)) // Отображаем часть содержимого

        Button(onClick = onDeleteClick) {
            Text("Удалить")
        }
    }
}
