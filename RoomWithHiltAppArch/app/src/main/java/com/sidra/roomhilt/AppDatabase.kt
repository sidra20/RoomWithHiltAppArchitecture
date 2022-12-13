package com.sidra.roomhilt

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sidra.roomhilt.datalayer.Note
import com.sidra.roomhilt.datalayer.local.NoteDao

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun noteDao() : NoteDao
}