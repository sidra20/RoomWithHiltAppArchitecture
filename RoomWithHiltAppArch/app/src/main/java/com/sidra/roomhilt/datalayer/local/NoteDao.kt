package com.sidra.roomhilt.datalayer.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sidra.roomhilt.datalayer.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun insetNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * FROM note ORDER BY id DESC")
    fun getAllNotes() : LiveData<List<Note>>


}