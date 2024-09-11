package com.tejdev.notesapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.tejdev.notesapp.Data.Database.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(context, NoteDatabase::class.java, "note_database").fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideNoteDao(noteDatabase: NoteDatabase) = noteDatabase.noteDao
}