package com.example.routinemanager.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.routinemanager.data.model.Task
import com.example.routinemanager.ui.viewmodel.TaskViewModel

/**
 * Экран со списком задач.
 */
@Composable
fun TaskListScreen(
    viewModel: TaskViewModel,
    onAddTaskClick: () -> Unit,
    onTaskClick: (String) -> Unit  // Добавили колбэк при клике на задачу
) {
    val taskList by viewModel.tasks.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Менеджер рутины") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddTaskClick) {
                Text("+")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            items(taskList) { task ->
                TaskItem(
                    task = task,
                    onCheck = { viewModel.toggleTaskDone(task) },
                    onDelete = { viewModel.deleteTask(task) },
                    onClick = { onTaskClick(task.id) }
                )
            }
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    onCheck: () -> Unit,
    onDelete: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            // Добавили возможность кликнуть по карточке
            .clickable { onClick() },
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Checkbox(
                checked = task.isDone,
                onCheckedChange = { onCheck() }
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(text = task.title, style = MaterialTheme.typography.subtitle1)
                if (!task.description.isNullOrEmpty()) {
                    Text(text = task.description, style = MaterialTheme.typography.body2)
                }
            }
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Task"
                )
            }
        }
    }
}
