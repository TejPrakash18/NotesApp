package com.tejdev.notesapp.ViewModel

import androidx.lifecycle.ViewModel
import com.tejdev.notesapp.Data.DAO.NoteDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteDao: NoteDao): ViewModel() {


    val notes = noteDao.getAllNotes()



}