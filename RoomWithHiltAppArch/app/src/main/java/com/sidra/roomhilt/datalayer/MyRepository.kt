package com.sidra.roomhilt.datalayer

import com.sidra.roomhilt.datalayer.local.NoteDao
import javax.inject.Inject

class MyRepository @Inject constructor(private val noteDao: NoteDao) {

    val notes = noteDao.getAllNotes()

    suspend fun insert(note: Note){
        noteDao.insetNote(note)
    }
    suspend fun delete(note: Note)
    {
        noteDao.deleteNote(note)
    }
    suspend fun update(note: Note)
    {
        noteDao.updateNote(note)
    }


}