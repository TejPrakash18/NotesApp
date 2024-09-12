package com.tejdev.notesapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tejdev.notesapp.Data.DAO.NoteDao
import com.tejdev.notesapp.Data.Entity.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteDao: NoteDao): ViewModel() {


    val notes = noteDao.getAllNotes()
    val notesChannel = Channel<NoteEvent>()
    var notesEvent = notesChannel.receiveAsFlow()



    fun insertNote(note: Note) = viewModelScope.launch {
        noteDao.insertNote(note)
        notesChannel.send(NoteEvent.navigateToNoteScreen)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteDao.deleteNote(note)
        notesChannel.send(NoteEvent.ShowSnackbar("Note Deleted"))
    }
    fun updateNote(note: Note) = viewModelScope.launch {
        noteDao.updateNote(note)
        notesChannel.send(NoteEvent.navigateToNoteScreen)
    }

    sealed class NoteEvent{
        data class ShowSnackbar(val message: String): NoteEvent()
        object navigateToNoteScreen: NoteEvent()

    }


}