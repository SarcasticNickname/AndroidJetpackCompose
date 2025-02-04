// File: MainActivity.kt

package com.myprojects.audionotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.myprojects.audionotes.data.local.db.AppDatabase
import com.myprojects.audionotes.data.repository.NotesRepositoryImpl
import com.myprojects.audionotes.domain.UseCases
import com.myprojects.audionotes.presentation.add_edit_note.AddEditNoteScreen
import com.myprojects.audionotes.presentation.add_edit_note.AddEditNoteViewModel
import com.myprojects.audionotes.presentation.notes_list.NotesScreen
import com.myprojects.audionotes.presentation.notes_list.NotesViewModel

class MainActivity : ComponentActivity() {

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "notes_db"
        ).build()
    }

    private val repository by lazy {
        NotesRepositoryImpl(database.noteDao())
    }

    private val useCases by lazy {
        UseCases(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "notes_list"
            ) {
                composable("notes_list") {
                    val notesVM = createViewModel { NotesViewModel(useCases) }
                    NotesScreen(
                        viewModel = notesVM,
                        onNoteClick = { noteId ->
                            navController.navigate("add_edit_note/$noteId")
                        },
                        onAddNoteClick = {
                            navController.navigate("add_edit_note")
                        }
                    )
                }

                // Маршрут для редактирования заметки
                composable(
                    route = "add_edit_note/{noteId}",
                    arguments = listOf(navArgument("noteId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val noteId = backStackEntry.arguments?.getString("noteId")
                    val addEditVM = createViewModel { AddEditNoteViewModel(useCases) }
                    AddEditNoteScreen(
                        viewModel = addEditVM,
                        noteId = noteId,
                        onSaveSuccess = { navController.popBackStack() }
                    )
                }

                // Маршрут для добавления новой заметки (без noteId)
                composable("add_edit_note") {
                    val addEditVM = createViewModel { AddEditNoteViewModel(useCases) }
                    AddEditNoteScreen(
                        viewModel = addEditVM,
                        noteId = null,
                        onSaveSuccess = { navController.popBackStack() }
                    )
                }
            }
        }
    }

    /**
     * Упрощённая функция-фабрика для ViewModel.
     */
    @Composable
    private inline fun <reified T : ViewModel> createViewModel(
        crossinline factory: () -> T
    ): T {
        return viewModel(
            factory = object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T1 : ViewModel> create(modelClass: Class<T1>): T1 {
                    return factory() as T1
                }
            }
        )
    }
}
