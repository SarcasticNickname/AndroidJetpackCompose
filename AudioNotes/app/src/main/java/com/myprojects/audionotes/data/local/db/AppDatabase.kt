// File: data/local/db/AppDatabase.kt

package com.myprojects.audionotes.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myprojects.audionotes.data.local.dao.NoteDao
import com.myprojects.audionotes.data.local.entity.NoteEntity

/**
 * RoomDatabase, в которой хранится таблица с заметками.
 * Расширяется по мере добавления новых сущностей.
 */
@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
