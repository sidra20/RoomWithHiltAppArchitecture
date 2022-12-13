package com.sidra.roomhilt.datalayer.di

import android.content.Context
import androidx.room.Room
import com.sidra.roomhilt.AppDatabase
import com.sidra.roomhilt.datalayer.Note
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, AppDatabase::class.java, "notedatabase")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun providesDao(db: AppDatabase) = db.noteDao()

    @Provides
    fun providesNote()=Note()
}