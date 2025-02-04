// File: data/local/dao/NoteDao.kt

package com.myprojects.audionotes.data.local.dao

import androidx.room.*
import com.myprojects.audionotes.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO (Data Access Object) для доступа к таблице с заметками.
 */
@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNoteById(id: String): Flow<NoteEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)
}
