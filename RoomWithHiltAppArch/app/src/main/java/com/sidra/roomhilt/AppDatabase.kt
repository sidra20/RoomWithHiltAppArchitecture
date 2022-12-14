package com.sidra.roomhilt

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sidra.roomhilt.datalayer.Note
import com.sidra.roomhilt.datalayer.local.NoteDao

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun noteDao() : NoteDao
//    companion object{
//        private var instance:AppDatabase?=null
//
//
//        fun getInstance(context: Context):AppDatabase
//        {
//            @Volatile
//            if(instance==null)
//            {
//
//
//                synchronized(this)
//                {
//
//
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        AppDatabase::class.java,
//                        "notedatabase"
//                    )
//                        .build()
//                }
//            }
//            return instance!!
//        }
//
//    }
}