package com.tejdev.notesapp.Data.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tejdev.notesapp.Data.DAO.NoteDao
import com.tejdev.notesapp.Data.Entity.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao

}